package com.real.cyd.controller.sys;

import com.real.cyd.bean.SysUser;
import com.real.cyd.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;

@Controller
public class UploadController {

    @Resource
    private UserService userService;

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

    @RequestMapping("/uploadAvatar")
    @ResponseBody
    public String uploadAvatar(@RequestParam("file")MultipartFile file,String id){
        System.out.println(file+"---"+file.getName()+"----"+file.getOriginalFilename() + id);
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
                String path = "E:/work/realEstateAgency/src/main/resources/static/images/";
                File files = new File(path+file.getOriginalFilename());
                if(!files.exists()){
                    files.createNewFile();
                }
                // BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
                FileUtils.copyInputStreamToFile(file.getInputStream(), files);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            String str = "{\"code\": 0,\"msg\": \"\",\"data\": {\"src\":\"../images/" + file.getOriginalFilename() + "\"}}";
            userService.upload(id,"images/" + file.getOriginalFilename());
            return str;
        }else{
            return"上传失败，因为文件是空的.";
        }
    }
}
