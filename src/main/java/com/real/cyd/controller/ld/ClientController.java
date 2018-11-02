package com.real.cyd.controller.ld;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.LdClient;
import com.real.cyd.bean.PageBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.req.ld.QueryClientReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClientService;
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
 * @create: 2018-03-18 15:40
 **/
    //客户controller
@Controller
@RequestMapping("/clientManager")
public class ClientController {
    @Resource
    private ClientService clientService;

    @RequestMapping("/queryList")
    @RequiresPermissions("clientList:List")//权限管理;
    public String queryList(){

        return "clientManager/clientList";
    }
    @RequestMapping("/clientList")
    @ResponseBody
    public RespBean clientList(PageBean page, QueryClientReq req){
        PageHelper.startPage(page.getPageNum(),page.getPageSize());
        PageHelper.orderBy("create_Time DESC");
        RespBean client =clientService.queryClientList(req);
        return client;
    }

    @RequestMapping("/toAdd")
    //@RequiresPermissions("userList:toAdd")
    public String toAdd(){
        return "clientManager/add";
    }

    @RequestMapping("/addUser")
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public RespBean addClient(LdClient client){

        RespBean users = clientService.insertClient(client);
        return users;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(String id){
        return clientService.deleteClient(id);
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(){

        return "clientManager/update";
    }

    @RequestMapping("/getClientInfo")
    @ResponseBody
    public RespBeanOneObj getClientInfo(String id){
        return clientService.getUserInfo(id);
    }
    @RequestMapping("/updateClient")
    @ResponseBody
    public RespBean updateClient(LdClient client){
        clientService.updateClient(client);
        RespBean users = new RespBean();
        users.setErrorNo("0");
        return users;
    }
}