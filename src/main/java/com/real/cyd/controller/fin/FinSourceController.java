package com.real.cyd.controller.fin;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.FinSource;
import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.FinSourceService;
import com.real.cyd.service.LaundryTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 19:45
 **/
@Controller
@RequestMapping("/finSource")
public class FinSourceController {
    @Resource
    private FinSourceService finSourceService;

    @RequestMapping("/queryList")
        @RequiresPermissions("finSource:List")//权限管理;
    public String queryList(){

        return "finSource/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean clothes(PageBean page){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("createTime DESC");
        RespBean client =finSourceService.queryList();
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "finSource/add";
    }

    @RequestMapping("/add")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean add(FinSource bean){
        finSourceService.insert(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return finSourceService.delete(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "finSource/update";
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public RespBeanOneObj getInfo(String id){
        return finSourceService.getInfo(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(FinSource bean){
        finSourceService.update(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }
}