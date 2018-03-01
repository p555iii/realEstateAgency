package com.real.cyd.controller.sys;

import com.real.cyd.bean.PropBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PropController {
    @Autowired
    private PropBean p;
    @RequestMapping("/test")
    public void test(){
        System.out.println(p.getGg()+"--"+p.getKk());

    }
}
