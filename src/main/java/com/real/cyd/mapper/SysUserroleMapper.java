package com.real.cyd.mapper;

import com.real.cyd.bean.SysUserrole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserroleMapper {



    int deleteByPrimaryKey(String id);

    int insert(SysUserrole record);

    int insertSelective(SysUserrole record);

    SysUserrole selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUserrole record);

    int updateByPrimaryKey(SysUserrole record);
}