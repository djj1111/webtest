package com.djj.test.control;

import com.djj.test.entity.BlobFile;
import com.djj.test.service.BlobFileService;
import com.djj.test.upload.Result;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by djj on 2017/2/10.
 */
@Controller

public class UploadControler {

    /* @Resource

     private Result result;*/
    @Resource(name = "blobFileService")
    private BlobFileService blobFileService;

    //@ResponseBody
    @RequestMapping(value = {"/download{fileID}"}, method = RequestMethod.GET)
    public String downloadFile(@PathVariable int fileID, HttpServletResponse response) throws IOException {
        BlobFile file = blobFileService.getBlobFile(fileID);
        response.setContentType(file.getType());
        response.setContentLength(file.getPhoto().length);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getText() + "\"");
        FileCopyUtils.copy(file.getPhoto(), response.getOutputStream());
        return "success";
    }


    @RequestMapping("/uploadfiletodatabase")

    @ResponseBody      //把回传类转换成json

    public Result uploadFileToDatabase(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        Result result = new Result();
        String path = uploadFile(file, request, 0);

        if (path.equals("io erros")) {
            result.setCode("400");
            result.setPath("");
            result.setMessage("上传不成功");
        } else {
            result.setCode("200");
            result.setPath(path);
            result.setMessage("上传成功");
        }
        return result;
    }

    @RequestMapping("/uploadfiletopath")

    @ResponseBody      //把回传类转换成json

    public Result uploadFileToPath(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        Result result = new Result();
        String path = uploadFile(file, request, 1);

        if (path.equals("io erros")) {
            result.setCode("400");
            result.setPath("");
            result.setMessage("上传不成功");
        } else {
            result.setCode("200");
            result.setPath(path);
            result.setMessage("上传成功");
        }

        return result;

    }

    /**
     * 上传
     *
     * @param file
     * @param request
     * @return
     * @throws IOException
     */

    private String uploadFile(MultipartFile file, HttpServletRequest request, int position) {
        String path = request.getSession().getServletContext().getRealPath("upload");

        String fileName = file.getOriginalFilename();
        if (position == 1) {


            File targetFile = new File(path, fileName);

            if (!targetFile.exists()) {

                targetFile.mkdirs();

            }

            try {
                file.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
                targetFile.delete();
                return "io error";
            }

//               request.setAttribute("filePath",targetFile.getAbsolutePath());
            return targetFile.getAbsolutePath();
        } else {
            BlobFile f = new BlobFile();
            try {
                f.setPhoto(file.getBytes());
                f.setText(fileName);
                f.setType(file.getContentType());
                f.setIp(request.getRemoteAddr());
                blobFileService.addBlobFile(f);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return "io error";
            }


        }


    }

    private String getRemortIP(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}
