package com.example.demo.controller;

import com.example.demo.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/common")

public class CommonController {

    @Value("${ruiji.path}")
    private String basePath;

    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        log.info(file.toString());

        String originalFilename = file.getOriginalFilename();

        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = UUID.randomUUID().toString() + suffix;

        File dir=new File(basePath);
        if(!dir.exists()){
            dir.mkdirs();
        }

        try {
            file.transferTo(new File(basePath+fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return R.sucess(fileName);
    }

    @GetMapping("/download")
    public void download(String name , HttpServletResponse response){

        try {
            //通过输入流读取文件内容

            FileInputStream fileInputStream=new FileInputStream(new File(basePath+name));

            //再通过输出流回写文件到浏览器上，所以需要拿response的输出流

            ServletOutputStream outputStream=response.getOutputStream();

            response.setContentType("image/jpeg");//设置响应回去的文件格式为图片

            byte[] bytes=new byte[1024];

            int len=0;

            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }

            fileInputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }


}
