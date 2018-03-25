package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.LdIntegral;
import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.IntegralService;
import com.real.cyd.service.LaundryTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: realEstateAgency
 * @description: 积分controller
 * @author: cyd
 * @create: 2018-03-21 15:34
 **/
@Controller
@RequestMapping("/integral")
public class IntegralController {
    @Resource
    private IntegralService integralService;

    @RequestMapping("/queryList")
    @RequiresPermissions("integral:List")//权限管理;
    public String queryList(){

        return "integral/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean list(PageBean page){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean client =integralService.queryList();
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "integral/add";
    }

    @RequestMapping("/add")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean add(LdIntegral bean){
        integralService.insert(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return integralService.delete(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "integral/update";
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public RespBeanOneObj getInfo(String id){
        return integralService.getInfo(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(LdIntegral bean){
        integralService.update(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }


}