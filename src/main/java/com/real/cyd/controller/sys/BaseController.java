package com.real.cyd.controller.sys;

import com.real.cyd.bean.vo.RecordBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: 用作基本数据的处理
 * @author: cyd
 * @create: 2018-03-25 20:14
 **/
@Controller
public class BaseController {
    @RequestMapping("/record")
    @ResponseBody
    public RespBeanOneObj record(){
        List<RecordBean> list = new ArrayList<>();
        list.add(new RecordBean("收入",0));
        list.add(new RecordBean("支出",1));
        return ToolsUtils.getRespOneObj(list);
    }
}