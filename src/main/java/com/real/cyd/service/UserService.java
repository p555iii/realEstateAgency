package com.real.cyd.service;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysUser;

import java.util.List;

public interface UserService {
    public int insertUser(SysUser user);
    public int updateUser(SysUser user);
    public int deleteUser(SysUser user);
    public RespBean queryUserList();
}
