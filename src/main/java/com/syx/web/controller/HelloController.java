package com.syx.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by shiyongxiang on 16/9/22.
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String welcome() {
        return "test";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file){
        try {
            FileUtils.writeByteArrayToFile(new File("/Users/shiyongxiang/IdeaProjects/spring-mvc/src/main/webapp/upload/"+file.getOriginalFilename()),file.getBytes());
            return "ok";
        } catch (IOException e) {
            e.printStackTrace();
            return "wrong";
        }
    }
}
