package com.djj.test.control;

import com.djj.test.entity.File;
import com.djj.test.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

        return fileService.getAllFile();
    }
}
