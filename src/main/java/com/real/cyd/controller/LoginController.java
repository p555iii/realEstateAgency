package com.real.cyd.controller;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.SysUser;
import com.real.cyd.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static java.lang.System.*;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/2
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserMapper userMapper;
    @RequestMapping("/")
    public String index(Model model) {
        // 加入一个属性，用来在模板中读取
        //map.addAttribute("host", "http://blog.didispace.com");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        model.addAttribute("abc","123");
        model.addAttribute("abcd","123");
        model.addAttribute("abce","123");
        PageHelper.startPage(1,10);
        List<SysUser> users = userMapper.userList();
       /* User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName("zhangs");
        user.setAge(12);
        userMapper.insertUser(user);
        for(User u:users){
            out.println(u.toString());
        }*/
        out.println("abcddsdsdseff");
        System.out.println(users.get(0).getId());
        System.out.println(getenv().get("JAVA_HOME"));
        return "index";
    }
    @RequestMapping("/err")
    public int error(){
        return 100/0;
    }
}
