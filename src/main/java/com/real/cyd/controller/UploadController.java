package com.real.cyd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Controller
public class UploadController {

    @RequestMapping("/file")
    public String file(){

        return "file";
    }

    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                /*
                 * 这段代码执行完毕之后，图片上传到了工程的跟路径；
                 * 大家自己扩散下思维，如果我们想把图片上传到 d:/files大家是否能实现呢？
                 * 等等;
                 * 这里只是简单一个例子,请自行参考，融入到实际中可能需要大家自己做一些思考，比如：
                 * 1、文件路径；
                 * 2、文件名；
                 * 3、文件格式;
                 * 4、文件大小的限制;
                 */
                String path = "E:/";
                File files = new File(path+file.getOriginalFilename());
                if(!files.exists()){
                    files.createNewFile();
                }
               // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                FileWriter fw = new FileWriter(files,true);
                System.out.println(file.getBytes().toString());
                fw.write(file.getBytes().toString());
                fw.flush();
                fw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            return"上传成功";
        }else{
            return"上传失败，因为文件是空的.";
        }
    }
}
