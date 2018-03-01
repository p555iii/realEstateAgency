package com.real.cyd.controller.sys;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.*;
import com.real.cyd.req.AuthPermissionReq;
import com.real.cyd.req.QueryRoleReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.SysRoleService;
import com.real.cyd.utils.ToolsUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 17:35
 **/
@Controller
@RequestMapping("/roleManager")
public class SysRoleController {
    @Resource
    private SysRoleService roleService;

    @RequestMapping("/queryRole")
    @RequiresPermissions("userList:List")//权限管理;
    public String queryRole(){

        return "roleManager/roleList";
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public RespBean roleList(PageBean page, QueryRoleReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("createTime DESC");
        RespBean roleList =roleService.queryRoleList(req);
        return roleList;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "roleManager/add";
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public RespBean addRole(SysRole role){
        roleService.insertRole(role);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return roleService.deleteUser(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "roleManager/update";
    }

    @RequestMapping("/getRoleInfo")
    @ResponseBody
    public RespBeanOneObj getRoleInfo(String id){
        return roleService.getRoleInfo(id);
    }
    @RequestMapping("/updateRole")
    @ResponseBody
    public RespBean updateRole(SysRole role){
        roleService.updateUser(role);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/perDistribution")
    public String perDistribution(String id,Model model){
        List<TreeMenuAllowAccess> list = roleService.perDistribution(id);
        model.addAttribute("list",list);
        model.addAttribute("id",id);
        return "roleManager/auth";
    }

    @RequestMapping("/authPer")
    @ResponseBody
    public RespBean authPer(AuthPermissionReq req){
        roleService.authPer(req);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/getRoles")
    @ResponseBody
    public RespBeanOneObj getRoles(){
        List<SysRole> role = roleService.getRole();
        return ToolsUtils.getRespOneObj(role);
    }
}