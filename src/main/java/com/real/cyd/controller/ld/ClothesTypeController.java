package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.LdClothesType;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClothesTypeService;
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
 * @create: 2018-03-19 14:12
 **/
@Controller
@RequestMapping("/clothesType")
public class ClothesTypeController {
    @Resource
    private ClothesTypeService clothesTypeService;

    @RequestMapping("/queryList")
    @RequiresPermissions("clothesType:List")//权限管理;
    public String queryList(){

        return "clothesType/clothesTypeList";
    }
    @RequestMapping("/clothes")
    @ResponseBody
    public RespBean clothes(PageBean page){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean client =clothesTypeService.queryClothesTypeList();
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "clothesType/add";
    }

    @RequestMapping("/addClothes")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean addClothes(LdClothesType clothesType){
        clothesTypeService.insertClothesType(clothesType);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return clothesTypeService.deleteClothesType(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "clothesType/update";
    }

    @RequestMapping("/getClothesTypeInfo")
    @ResponseBody
    public RespBeanOneObj getClothesTypeInfo(String id){
        return clothesTypeService.getClothesTypeInfo(id);
    }
    @RequestMapping("/updateClothesType")
    @ResponseBody
    public RespBean updateClothesType(LdClothesType clothesType){
        clothesTypeService.updateClothesType(clothesType);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/clothList")
    @ResponseBody
    public RespBeanOneObj clothList(){
        return  clothesTypeService.clothList();
    }
}