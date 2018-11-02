package com.real.cyd.controller.sys;

import com.github.pagehelper.PageHelper;
import com.real.cyd.bean.SysPermissionTree;
import com.real.cyd.bean.SysUser;
import com.real.cyd.mapper.SysUserMapper;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.service.SysPermissionService;
import com.real.cyd.utils.ShiroUtil;
import com.real.cyd.utils.SpringUtils;
import com.real.cyd.utils.ToolsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.List;
import java.util.Map;

import static java.lang.System.*;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/2
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserMapper userMapper;
    // 登录提交地址和applicationontext-shiro.xml配置的loginurl一致。 (配置文件方式的说法)
    @RequestMapping(value="/login")
    public String loginUser(String username,String password,HttpSession session,HttpServletRequest request,Model model) {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);

        if(ToolsUtils.IsNull(username)&&ToolsUtils.IsNull(password)){
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        try {
            SysUser me = null;
            subject.login(usernamePasswordToken);   //完成登录
            SysUser user=(SysUser) subject.getPrincipal();
            session.setAttribute("user", user);
            model.addAttribute("user",user);
            /**
             * 保存登录信息
             */

            try{
                me =  ShiroUtil.getSessionUser();
            }catch (UnavailableSecurityManagerException e){

            }
            if(me == null){

                return "login";
            }
            me.setPassword("");


            List<SysPermissionTree> treeMenus = SpringUtils.getBean(SysPermissionService.class).selectTreeMenuByUserId(me.getId());

            session.setAttribute("treeMenus", treeMenus);
            return "index";
        } catch(Exception e) {
            model.addAttribute("msg","用户名或密码错误");
            return "login";//返回登录页面
        }

    }
    @RequestMapping("/err")
    public int error(){
        return 100/0;
    }
}
