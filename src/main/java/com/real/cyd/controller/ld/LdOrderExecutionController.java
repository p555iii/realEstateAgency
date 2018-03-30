package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.LdOrderexecution;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.LaundryTypeService;
import com.real.cyd.service.LdOrderExecutionService;
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
 * @create: 2018-03-29 11:58
 **/
@Controller
@RequestMapping("/orderExecution")
public class LdOrderExecutionController {
    @Resource
    private LdOrderExecutionService ldOrderExecutionService;

    @RequestMapping("/queryList")
    @RequiresPermissions("orderExecution:List")//权限管理;
    public String queryList(){

        return "orderExecution/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public RespBean clothes(PageBean page,QueryOrderReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("state,is_take_away ASC,create_Time DESC");
        RespBean client =ldOrderExecutionService.queryList(req);
        return client;
    }

    @RequestMapping("/toDetails")
    public String toDetails(){
        return "orderExecution/details";
    }

    @RequestMapping("/details")
    @ResponseBody
    public RespBean details(PageBean page,QueryDetailsReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean details = ldOrderExecutionService.details(req);
        return details;
    }

    @RequestMapping("/complete")
    @ResponseBody
    public RespBean complete(String infoId,String orderId){
        return ldOrderExecutionService.complete(infoId,orderId);
    }

    @RequestMapping("/completeOrder")
    @ResponseBody
    public RespBean completeOrder(String id){
        return ldOrderExecutionService.completeOrder(id);
    }
    //outbound
    @RequestMapping("/outbound")
    @ResponseBody
    public RespBean outbound(String id){
        return ldOrderExecutionService.outbound(id);
    }
}