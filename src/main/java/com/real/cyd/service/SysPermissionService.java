package com.real.cyd.service;

import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysPermission;
import com.real.cyd.bean.SysPermissionTree;
import com.real.cyd.bean.SysRole;
import com.real.cyd.req.QueryPermissionReq;
import com.real.cyd.resp.RespBeanOneObj;

import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 17:45
 **/
public interface SysPermissionService {
    List<SysPermission> getPermissions(SysRole role);

    List<SysPermissionTree> selectTreeMenuByUserId(String id);

    RespBean queryPermissionList(QueryPermissionReq req);

    void insertPermission(SysPermission permission);

    RespBean deletePermission(String id);

    RespBeanOneObj getPermissionInfo(String id);

    void updatePermission(SysPermission permission);

    List<SysPermission> queryParentPermission();
}