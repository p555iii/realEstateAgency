package com.real.cyd.mapper;

import com.real.cyd.bean.SysPermission;
import com.real.cyd.bean.SysRole;
import com.real.cyd.req.QueryPermissionReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
public interface SysPermissionMapper {


    int deleteByPrimaryKey(String id);

    int insert(SysPermission record);

    int insertSelective(SysPermission record);

    SysPermission selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysPermission record);

    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> getPermissions(SysRole role);

    List<SysPermission> getPermissionsByDeep1(SysRole role);

    List<SysPermission> getPermissionsByDeep2(Map<String, String> map);

    List<SysPermission> perList(QueryPermissionReq req);

    int getCount(QueryPermissionReq req);

    List<SysPermission> queryParentPermission();

    int getMaxSortParent();

    int getMaxSortChildern(SysPermission permission);
}