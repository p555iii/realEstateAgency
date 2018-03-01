package com.real.cyd.utils;

import com.real.cyd.bean.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-27 11:36
 **/
public class ShiroUtil {
    /**
     * 密码加密
     * @param password
     * @param salt
     * @return
     */
    public static String md51024Pwd(String password,Object salt){
        return new SimpleHash("MD5", password, salt, 1024).toString();
    }

    /**
     * 获取当前Session中的用户
     * @return
     */
    public static SysUser getSessionUser(){

        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            Object object = subject.getPrincipal();
            if(object != null){
                SysUser sysUser = (SysUser) object;
                return sysUser;
            }
        }
        return null;
    }

    /**
     * 获取当前用户ID
     * @return
     */
    public static String getSessionUid(){

        SysUser sysUser = getSessionUser();

        if(sysUser != null){

            return sysUser.getId();
        }

        return null;
    }
}