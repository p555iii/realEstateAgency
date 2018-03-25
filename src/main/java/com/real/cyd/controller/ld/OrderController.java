package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.*;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.LaundryTypeService;
import com.real.cyd.service.OrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 11:12
 **/
@Controller
@RequestMapping("/order")
public class OrderController {
    @Resource
    private OrderService orderService;

    @RequestMapping("/queryList")
    @RequiresPermissions("order:List")//权限管理;
    public String queryList(){

        return "order/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean clothes(PageBean page, QueryOrderReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean client =orderService.queryList(req);
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "order/add";
    }

    @RequestMapping("/add")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean add(LdOrder bean){

        return orderService.insert(bean);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return orderService.delete(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "order/update";
    }

    @RequestMapping("/getInfo")
    @ResponseBody
    public RespBeanOneObj getInfo(String id){
        return orderService.getInfo(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(LdLaundryType bean){
        orderService.update(bean);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }

    @RequestMapping("/toDetails")
    public String toDetails(){
        return "order/details";
    }

    @RequestMapping("/details")
    @ResponseBody
    public RespBean details(PageBean page,QueryDetailsReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean details = orderService.details(req);
        return details;
    }

    @RequestMapping("/toInfoAdd")
    public String toInfoAdd(String orderId){

        return "order/infoAdd";
    }

    @RequestMapping("/infoAdd")
    @ResponseBody
    public RespBean infoAdd(LdOrderInfo bean){
        return orderService.infoAdd(bean);
    }

    @RequestMapping("/toInfoUpdate")
    public String toInfoUpdate(){
        return "order/infoUpdate";
    }

    @RequestMapping("/getInfoInfo")
    @ResponseBody
    public RespBeanOneObj getInfoInfo(String infoId){
        return orderService.getInfoInfo(infoId);
    }

    @RequestMapping("/infoUpdate")
    @ResponseBody
    public RespBean infoUpdate(LdOrderInfo bean){
        return orderService.infoUpdate(bean);
    }

    @RequestMapping("/completeOrder")
    public String completeOrder(){
        return "order/completeOrder";
    }

    @RequestMapping("/getEmTime")
    @ResponseBody
    public RespBeanOneObj getEmTime(String orderId){

        return orderService.getEmTime(orderId);
    }

    @RequestMapping("/complete")
    @ResponseBody
    public RespBean complete(LdOrder order){
        return orderService.complete(order);
    }
}