package com.real.cyd.mapper;

import com.real.cyd.bean.SysPermissionrole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysPermissionroleMapper {


    int insert(SysPermissionrole record);

    int insertSelective(SysPermissionrole record);

}