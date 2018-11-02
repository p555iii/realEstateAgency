package com.real.cyd.controller.ld;

import com.real.cyd.bean.*;
import com.real.cyd.mapper.LdClientMapper;
import com.real.cyd.mapper.LdClothesTypeMapper;
import com.real.cyd.mapper.LdLaundryTypeMapper;
import com.real.cyd.service.ClientService;
import com.real.cyd.service.OrderService;
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
 * @create: 2018-04-06 10:54
 **/
@Controller
@RequestMapping("/web")
public class WebController {

    @Resource
    private LdClientMapper clientMapper;
    @Resource
    private OrderService orderService;
    @Resource
    private LdClothesTypeMapper ldClothesTypeMapper;
    @Resource
    private LdLaundryTypeMapper ldLaundryTypeMapper;

    @RequestMapping("/login")
    public String login(){
        return "web/login";
    }

    @RequestMapping("/toIndex")
    public String toIndex(String phone, Model model){
        if(phone == null || phone.equals("")){
            model.addAttribute("info","手机号为空");
            return "web/login";
        }
        LdOrder order = new LdOrder();
        order.setPhone(phone);
        LdClient c = clientMapper.queryClientByPhone(order);
        if(c == null){
            model.addAttribute("info","抱歉，您不是我店会员无法预约。");
            return "web/login";
        }
        model.addAttribute("phone",phone);
        return "web/index";
    }

    @RequestMapping("/toOrder")
    public String toOrder(String phone,Model model){
        //创建一个预约
        LdOrder order = orderService.addReservation(phone);
        List<LdClothesType> clothesTypeList = ldClothesTypeMapper.list();
        List<LdLaundryType> ldLaundryTypeList = ldLaundryTypeMapper.list();
        model.addAttribute("clothesTypeList",clothesTypeList);
        model.addAttribute("ldLaundryTypeList",ldLaundryTypeList);
        model.addAttribute("orderId",order.getId());
        return "web/select";
    }
    @RequestMapping("/addInfo")
    @ResponseBody
    public String addInfo(LdOrderInfo orderInfo){
        orderService.addReservationInfo(orderInfo);
        return "";
    }
}
