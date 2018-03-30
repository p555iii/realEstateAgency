package com.real.cyd.controller.sys;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysUser;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.resp.vo.UserRoleInfoVo;
import com.real.cyd.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userManager")
public class SysUserController {
    @Resource
    private UserService userService;

    @RequestMapping("/queryList")
    @RequiresPermissions("userList:List")//权限管理;
    public String queryList(){

        return "userManager/userList";
    }

    @RequestMapping("/userList")
    @ResponseBody
    public RespBean userList(PageBean page,QueryUserReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("createTime DESC");
        RespBean users =userService.queryUserList(req);
        return users;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "userManager/add";
    }

    @RequestMapping("/addUser")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean addUser(SysUser user){
        userService.insertUser(user);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return userService.deleteUser(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "userManager/update";
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public RespBeanOneObj getUserInfo(String id){
        return userService.getUserInfo(id);
    }
    @RequestMapping("/updateUser")
    @ResponseBody
    public RespBean updateUser(SysUser user){
        userService.updateUser(user);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/toDistRole")
    public String toDistRole(){
        return "userManager/distRole";
    }

    @RequestMapping("/getUserRoleInfo")
    @ResponseBody
    public RespBeanOneObj getUserRoleInfo(String id){
        return userService.getUserRoleInfo(id);
    }

    @RequestMapping("/updateUserRole")
    @ResponseBody
    public RespBean updateUserRole(UserRoleInfoVo vo){
        userService.updateUserRole(vo);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/toUserInfo")
    public String toUserInfo(){
        return "userManager/userInfo";
    }
}
