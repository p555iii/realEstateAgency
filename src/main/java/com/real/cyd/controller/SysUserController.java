package com.real.cyd.controller;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.*;
import com.real.cyd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userManager")
public class SysUserController {
    @Resource
    private UserService userService;
    @RequestMapping("/insert")
    public void insertUser(SysUser user){
        userService.insertUser(user);
    }
    @RequestMapping("/queryList")
    public String queryList(){

        return "userManager/userList";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public RespBean userList(PageBean page){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        RespBean users =userService.queryUserList();
        return users;
    }
}
