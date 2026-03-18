package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator operator;
    @PostMapping("/upload")
    public Result upload( MultipartFile file) throws Exception {
        log.info("接收参数：{}",file.getOriginalFilename());
        //保存文件
//        String originalFilename = file.getOriginalFilename();
//        String extension=originalFilename.substring(originalFilename.lastIndexOf( "."));
//        String newFileName= UUID.randomUUID().toString() + extension;
//        file.transferTo(new File("D:/images/" + newFileName));
        //将文件交给OSS存储管理
        String upload = operator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("保存成功：{}",upload);
        return Result.success(upload);
    }
}
