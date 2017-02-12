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
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by djj on 2017/2/10.
 */
@Controller

public class UploadControler {

    /* @Resource

     private Result result;*/
    @Resource(name = "blobFileService")
    private BlobFileService blobFileService;

    @ResponseBody
    @RequestMapping("/downloadfilename{id}")
    public String getfilename(@PathVariable int id) throws IOException {
        return blobFileService.getFileName(id);

        //return simpleDateFormat.format(t);
    }

    //@ResponseBody
    @RequestMapping(value = {"/download{fileID}"}, method = RequestMethod.GET)
    public String downloadFile(@PathVariable int fileID, HttpServletResponse response) throws IOException {
        BlobFile file = blobFileService.getBlobFile(fileID);
        response.setContentType(file.getType());
        response.setContentLength(file.getPhoto().length);
        //response头中的字符要转码成UTF-8，java.net.URLEncode.encode.
        response.setHeader("Content-Disposition", "attachment; filename=\"" + java.net.URLEncoder.encode(file.getText(), "UTF-8") + "\"");
        FileCopyUtils.copy(file.getPhoto(), response.getOutputStream());
        return "success";
    }

    @ResponseBody
    @RequestMapping("/downloadtest{id}")
    public String showtime(@PathVariable int id) throws IOException {
        BlobFile file = blobFileService.getBlobFile(id);
        /*SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ssSSS");
        try{
            return simpleDateFormat.parse(file.getTime().);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }*/
        Timestamp t = Timestamp.valueOf(file.getTime());
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        return simpleDateFormat.format(t);
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
                f.setIp(getIpAddr(request));
                blobFileService.addBlobFile(f);
                return fileName;
            } catch (IOException e) {
                e.printStackTrace();
                return "io error";
            }


        }


    }

    //防止用户用代理
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
