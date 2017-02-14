package com.djj.test.control;

import com.djj.test.entity.File;
import com.djj.test.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by djj on 2017/2/5.
 */

@Controller
@RequestMapping("/file")
public class FileControler {
    @Resource(name = "fileService")
    private FileService fileService;

    @ResponseBody
    @RequestMapping("/getallfiles")
    public List<File> getAllFile(HttpServletRequest request) {

        // request.setAttribute("userList", userManager.getAllUser());

        return fileService.getAllFiles();
    }

    @ResponseBody
    @RequestMapping("/getfilebyid-{id}")
    public File getFilebyId(@PathVariable int id) {

        // request.setAttribute("userList", userManager.getAllUser());

        return fileService.getFileByID(id);
    }

    @ResponseBody
    @RequestMapping("/getfilepath-{id}")
    public String getFilePathbyId(@PathVariable int id) {

        // request.setAttribute("userList", userManager.getAllUser());

        return fileService.getFileByID(id).getPath();
    }

    @ResponseBody
    @RequestMapping(value = {"/addfile"}, method = RequestMethod.POST)
    public String saveFile(HttpServletRequest request) {
        Enumeration<String> a = request.getParameterNames();
        String parm = null;
        String val = "";
        File f = new File();
        while (a.hasMoreElements()) {
            //参数名
            parm = a.nextElement();
            switch (parm) {
                case "mid":
                    f.setMid(Integer.valueOf(request.getParameter(parm)));
                    break;
                case "path":
                    f.setPath(request.getParameter(parm));
                    break;
                default:
                    break;
            }
        }

        return String.valueOf(fileService.addFile(f));
        //return fileService.getAllFile();
    }

    /*@ResponseBody
    @RequestMapping("/updatefileby{id}")
    public String updateFileById(@PathVariable int id) {

        // request.setAttribute("userList", userManager.getAllUser());

        fileService.updateFileById(id);
        return "success";
    }*/

}