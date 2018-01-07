package com.real.cyd.controller;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.User;
import com.real.cyd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/userManager")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping("/insert")
    public void insertUser(User user){
        userService.insertUser(user);
    }
    @RequestMapping("/queryList")
    public String queryList(Model model, PageBean page){
        PageHelper.startPage(page.getPage(),page.getLimit());
        List<User> users =userService.queryUserList();
        model.addAttribute("list",users);
        return "userManager/userList";
    }
    @RequestMapping("/userList")
    @ResponseBody
    public ResBean userList(PageBean page){
        List<User> users =userService.queryUserList();
        ResBean res = new ResBean();
        res.setCode("0");
        res.setCount(1);
        res.setData(users);
        return res;
    }
}
