package com.real.cyd.controller.fin;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.*;
import com.real.cyd.req.fin.FinRecordReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.FinRecordedService;
import com.real.cyd.service.FinSourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 20:52
 **/
@Controller
@RequestMapping("/finRecorded")
public class FinRecordedController {
    @Resource
    private FinRecordedService finRecordedService;

    @RequestMapping("/queryList")
    @RequiresPermissions("finRecorded:List")//权限管理;
    public String queryList(){

        return "finRecorded/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean clothes(PageBean page,FinRecordReq record){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("createTime DESC");
        RespBean client =finRecordedService.queryList(record);
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "finRecorded/add";
    }

    @RequestMapping("/add")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean add(HttpSession session,FinRecorded bean){
        SysUser user = (SysUser) session.getAttribute("user");
        bean.setUserid(user.getId());
        finRecordedService.insert(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return finRecordedService.delete(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "finRecorded/update";
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public RespBeanOneObj getInfo(String id){
        return finRecordedService.getInfo(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(FinRecorded bean){
        finRecordedService.update(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }
}