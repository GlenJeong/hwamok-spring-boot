package com.hwamok.controller;

import com.hwamok.core.integreation.aws.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class AwsController {


    private S3Service s3Service;

    public AwsController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String fileUpload(List<MultipartFile> files){
        System.out.println("files = " + files);

        s3Service.upload(files);

        return "redirect:/";
    }
}
