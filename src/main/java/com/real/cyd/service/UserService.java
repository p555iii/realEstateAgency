package com.real.cyd.service;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysUser;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.resp.vo.UserRoleInfoVo;

import java.util.List;

public interface UserService {
    public RespBean insertUser(SysUser user);
    public RespBean updateUser(SysUser user);
    public int deleteUser(SysUser user);
    public RespBean queryUserList(QueryUserReq user);

    RespBean deleteUser(String id);

    RespBeanOneObj getUserInfo(String id);

    SysUser findByUsername(String username);

    RespBeanOneObj getUserRoleInfo(String id);

    void updateUserRole(UserRoleInfoVo vo);

    void upload(String id, String s);
}
