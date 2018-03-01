package com.real.cyd.controller.sys;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysPermission;
import com.real.cyd.bean.SysRole;
import com.real.cyd.req.QueryPermissionReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.SysPermissionService;
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
 * @create: 2018-02-27 17:04
 **/
@Controller
@RequestMapping("/perManager")
public class SysPermissionController {
    @Resource
    private SysPermissionService permissionService;

    @RequestMapping("/queryPer")
    @RequiresPermissions("perList:List")//权限管理;
    public String queryRer(){

        return "perManager/perList";
    }

    @RequestMapping("/perList")
    @ResponseBody
    public RespBean PermissionList(PageBean page, QueryPermissionReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("createTime DESC");
        RespBean roleList =permissionService.queryPermissionList(req);
        return roleList;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){

        return "perManager/add";
    }

    @RequestMapping("/addPer")
    @ResponseBody
    public RespBean addPer(SysPermission permission){
        permissionService.insertPermission(permission);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return permissionService.deletePermission(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){
        return "perManager/update";
    }

    @RequestMapping("/getPerInfo")
    @ResponseBody
    public RespBeanOneObj getPerInfo(String id){
        return permissionService.getPermissionInfo(id);
    }
    @RequestMapping("/updatePer")
    @ResponseBody
    public RespBean updatePer(SysPermission permission){
        permissionService.updatePermission(permission);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/getParentPer")
    @ResponseBody
    public RespBeanOneObj getParentPer(){
        List<SysPermission> permissionList = permissionService.queryParentPermission();
        return ToolsUtils.getRespOneObj(permissionList);
    }
}