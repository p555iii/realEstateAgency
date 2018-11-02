package com.real.cyd.mapper;

import com.real.cyd.bean.SysUser;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.resp.vo.UserRoleInfoVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserMapper {

    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> userList(QueryUserReq user);

    int getCount(QueryUserReq user);

    int delectUser(String id);

    SysUser findByUserName(SysUser user);

    UserRoleInfoVo getUserRoleInfo(SysUser user);

    void deleteUserRole(UserRoleInfoVo vo);

    void addUserRole(UserRoleInfoVo vo);

    void upload(@Param("id") String id,@Param("url") String s);

    SysUser selectByUsername(@Param("username")String username);

    void updateState(SysUser user);
}