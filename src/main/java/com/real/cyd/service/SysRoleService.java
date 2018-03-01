package com.real.cyd.service;

import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysRole;
import com.real.cyd.bean.SysUser;
import com.real.cyd.bean.TreeMenuAllowAccess;
import com.real.cyd.req.AuthPermissionReq;
import com.real.cyd.req.QueryRoleReq;
import com.real.cyd.resp.RespBeanOneObj;

import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 17:37
 **/
public interface SysRoleService {
    List<SysRole> getRoleList(SysUser userInfo);

    RespBean queryRoleList(QueryRoleReq req);

    void insertRole(SysRole role);

    RespBean deleteUser(String id);

    RespBeanOneObj getRoleInfo(String id);

    void updateUser(SysRole role);

    List<TreeMenuAllowAccess> perDistribution(String id);

    void authPer(AuthPermissionReq req);

    List<SysRole> getRole();
}