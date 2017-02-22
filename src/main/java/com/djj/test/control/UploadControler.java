package com.djj.test.control;

import com.djj.test.entity.BlobFile;
import com.djj.test.service.BlobFileService;
import com.djj.test.upload.Result;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        return blobFileService.getBlobFile(id).getText();

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


    @RequestMapping("/uploadfiletopath")

    @ResponseBody      //把回传类转换成json

    public Result uploadFileToPath(/*HttpServletRequest request*/@RequestParam("files") List<MultipartFile> files, HttpServletRequest request) throws IOException {
        // 判断文件是否为空
        Result result1 = new Result();

        if (files == null || files.isEmpty()) {
            result1.setCode("500");
            result1.setPath("");
            result1.setMessage("上传故障，内容为空");
            return result1;
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径
                    String filePath = request.getSession().getServletContext().getRealPath("/") + "fileupload/temp/"
                            + file.getOriginalFilename();
                    // 转存文件
                    file.transferTo(new File(filePath));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        // 重定向
        result1.setCode("200");
        result1.setPath("");
        result1.setMessage("success");
        return result1;

        /*boolean isFileUpload = ServletFileUpload.isMultipartContent(request);//检测是否存在文件上传的请求
        List<Result> result = new ArrayList();
        if (isFileUpload) {
            // 处理磁盘文件工厂类,param:低于sizethreshold走内存，高于存在repository位置
            //设置上传临时文件回收器
            FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getServletContext());
            DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024 * 1024, null);

            factory.setFileCleaningTracker(fileCleaningTracker);
            // 文件上传的处理类
            ServletFileUpload upload = new ServletFileUpload(factory);
            //-1 无限
            //upload.setFileSizeMax(1000000000L);
            //request maxsize
            //upload.setSizeMax();

            //解析上传的文件
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (Iterator iterator = fileItems.iterator(); iterator
                        .hasNext(); ) {
                    FileItem fileItem = (FileItem) iterator.next();
                    //判断上传的表单域是否为文件上传,false 表示文件上传<input type="file" />
                    //true 表示为非文件上传
                    if (fileItem.isFormField()) {
                        //对非文件上传的处理
                        if ("userName".equals(fileItem.getFieldName())) {
                        }
                    } else {
                        String contentType = fileItem.getContentType()
                                .toLowerCase();// 比如：image/pjpg
                        //进行格式的判断
                        if *//*(contentType.indexOf("jpg") != -1
                                || contentType.indexOf("jpeg") != -1
                                || contentType.indexOf("png") != -1
                                || contentType.indexOf("gif") != -1 || contentType.indexOf("bmp") !=-1)*//* (true) {
                            //对文件上传的处理
                            String filename = fileItem.getName();
                            if (filename.equals("")) continue;
                            // 上传之后文件的名字要唯一：采用当前上传的系统时间的毫秒数作为文件的名字
                            filename = System.currentTimeMillis() + "-" + filename;// 当前系统时间
                            //String postfix = fileUploadPath.substring(fileUploadPath.lastIndexOf("."));//获取文件的后缀名
                            //String fileName = time + postfix;
                            String contextPath = request.getServletContext().getRealPath("fileupload/temp");//获取工程中的名为imageTemp的目录
                            File dir = new File(contextPath);
                            dir.mkdirs();
                            String filePath = contextPath + File.separator + filename;
                            File file = new File(filePath);//代表上传文件的目录
                            FileOutputStream out = new FileOutputStream(file);
                            InputStream in = fileItem.getInputStream();
                            IOUtils.copy(in, out);
                            IOUtils.closeQuietly(out);//关闭输出流
                            IOUtils.closeQuietly(in);//关闭输出流
                            Result result1 = new Result();
                            result1.setCode("200");
                            result1.setPath(file.getAbsolutePath());
                            result1.setMessage("上传成功");
                            result.add(result1);
                        } else {
                            System.out.println("文件格式错误");
                        }
                    }

                }
            } catch (FileUploadException e) {
                System.out.println("文件上传异常");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (result.isEmpty()) {
                    Result result1 = new Result();
                    result1.setCode("500");
                    result1.setPath("");
                    result1.setMessage("上传故障，内容为空");
                    result.add(result1);
                }
            }
            return result;
        } else {
            Result result1 = new Result();
            result1.setCode("500");
            result1.setPath("");
            result1.setMessage("非上传文件请求");
            result.add(result1);
            return result;
        }*/

        /*Result result = new Result();
        if (file == null) {
            result.setCode("500");
            result.setPath("");
            result.setMessage("error");
            return result;
        }
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
        return result;*/
    }

    @RequestMapping("/uploadfiletodatabase")

    @ResponseBody      //把回传类转换成json

    public List<Result> uploadFileToDatabase(HttpServletRequest request) throws IllegalStateException, IOException {
        boolean isFileUpload = ServletFileUpload.isMultipartContent(request);//检测是否存在文件上传的请求
        List<Result> result = new ArrayList();
        if (isFileUpload) {
            // 处理磁盘文件工厂类,param:低于sizethreshold走内存，高于存在repository位置
            //设置上传临时文件回收器
            FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(request.getServletContext());
            DiskFileItemFactory factory = new DiskFileItemFactory(10 * 1024 * 1024, null);

            factory.setFileCleaningTracker(fileCleaningTracker);
            // 文件上传的处理类
            ServletFileUpload upload = new ServletFileUpload(factory);
            //-1 无限
            //upload.setFileSizeMax(1000000000L);
            //request maxsize
            //upload.setSizeMax();

            //解析上传的文件
            try {
                List<FileItem> fileItems = upload.parseRequest(request);
                for (Iterator iterator = fileItems.iterator(); iterator
                        .hasNext(); ) {
                    FileItem fileItem = (FileItem) iterator.next();
                    //判断上传的表单域是否为文件上传,false 表示文件上传<input type="file" />
                    //true 表示为非文件上传
                    if (fileItem.isFormField()) {
                        //对非文件上传的处理
                        if ("userName".equals(fileItem.getFieldName())) {
                        }
                    } else {
                        String contentType = fileItem.getContentType()
                                .toLowerCase();// 比如：image/pjpg
                        //进行格式的判断
                        if /*(contentType.indexOf("jpg") != -1
                                || contentType.indexOf("jpeg") != -1
                                || contentType.indexOf("png") != -1
                                || contentType.indexOf("gif") != -1 || contentType.indexOf("bmp") !=-1)*/ (true) {
                            //对文件上传的处理
                            String filename = fileItem.getName();
                            if (filename.equals("")) continue;
                            // 上传之后文件的名字要唯一：采用当前上传的系统时间的毫秒数作为文件的名字
                          /*  filename = System.currentTimeMillis() + "-" + filename;// 当前系统时间
                            //String postfix = fileUploadPath.substring(fileUploadPath.lastIndexOf("."));//获取文件的后缀名
                            //String fileName = time + postfix;
                            String contextPath = request.getServletContext().getRealPath("fileupload/temp");//获取工程中的名为imageTemp的目录
                            File dir = new File(contextPath);
                            dir.mkdirs();
                            String filePath = contextPath + File.separator + filename;
                            File file = new File(filePath);//代表上传文件的目录
                            FileOutputStream out = new FileOutputStream(file);*/
                            InputStream in = fileItem.getInputStream();
                            byte[] photo = new byte[(int) fileItem.getSize()];
                            in.read(photo);
                            in.close();
                            BlobFile f = new BlobFile();
                            f.setPhoto(photo);
                            f.setText(filename);
                            f.setType(fileItem.getContentType());
                            f.setIp(getIpAddr(request));
                            blobFileService.addBlobFile(f);

                            Result result1 = new Result();
                            result1.setCode("200");
                            result1.setPath(filename);
                            result1.setMessage("上传成功");
                            result.add(result1);
                        } else {
                            System.out.println("文件格式错误");
                        }
                    }

                }
            } catch (FileUploadException e) {
                System.out.println("文件上传异常");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (result.isEmpty()) {
                    Result result1 = new Result();
                    result1.setCode("500");
                    result1.setPath("");
                    result1.setMessage("上传故障，内容为空");
                    result.add(result1);
                }
            }
            return result;
        } else {
            Result result1 = new Result();
            result1.setCode("500");
            result1.setPath("");
            result1.setMessage("非上传文件请求");
            result.add(result1);
            return result;
        }

        /*Result result = new Result();
        if (file == null) {
            result.setCode("500");
            result.setPath("");
            result.setMessage("error");
            return result;
        }
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
        return result;*/
    }

    /*public Result uploadFileToPath(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
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

    }*/

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
