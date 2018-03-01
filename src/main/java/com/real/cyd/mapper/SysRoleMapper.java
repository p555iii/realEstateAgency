package com.real.cyd.mapper;

import com.real.cyd.bean.SysPermission;
import com.real.cyd.bean.SysRole;
import com.real.cyd.bean.SysUser;
import com.real.cyd.req.AuthPermissionReq;
import com.real.cyd.req.QueryRoleReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface SysRoleMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> getRoleList(SysUser userInfo);

    List<SysRole> getRoleById(SysUser user);

    List<SysRole> roleList(QueryRoleReq req);

    int getCount(QueryRoleReq req);

    List<SysPermission> selectPermissionParent();

    List<SysPermission> selectPermissionChildren(SysPermission permission);

    int selectThisPermissonAuth(Map<String, String> map);

    void deleteAuthByRoleId(AuthPermissionReq req);

    void authPer(Map<String, String> map);

    List<SysRole> getRole();
}