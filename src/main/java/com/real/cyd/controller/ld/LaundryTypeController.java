package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.LdClothesType;
import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClothesTypeService;
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
 * @create: 2018-03-19 14:38
 **/
@Controller
@RequestMapping("/laundryType")
public class LaundryTypeController {
    @Resource
    private LaundryTypeService laundryTypeService;

    @RequestMapping("/queryList")
    @RequiresPermissions("laundryType:List")//权限管理;
    public String queryList(){

        return "laundryType/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean clothes(PageBean page){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean client =laundryTypeService.queryList();
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "laundryType/add";
    }

    @RequestMapping("/add")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean add(LdLaundryType bean){
        laundryTypeService.insert(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return laundryTypeService.delete(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "laundryType/update";
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public RespBeanOneObj getInfo(String id){
        return laundryTypeService.getInfo(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(LdLaundryType bean){
        laundryTypeService.update(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/laundryList")
    @ResponseBody
    public RespBeanOneObj laundryList(){
        return laundryTypeService.laundryList();
    }
}