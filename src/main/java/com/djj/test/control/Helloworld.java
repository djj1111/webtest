package com.djj.test.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequestMapping("/fuck")
@Controller
public class Helloworld {

    @RequestMapping("/helloworld")  //此处控制浏览器里访问路径 具体为：/SpringDemo/helloworld
    public void helloWorld(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //输出字符串
        response.getWriter().append("hello world");

    }
    @RequestMapping("/hello1")
    public String hello1(){
        return "success";
    }
    @ResponseBody
    @RequestMapping("/json")
    public String json(){
        return "json";
    }


}
