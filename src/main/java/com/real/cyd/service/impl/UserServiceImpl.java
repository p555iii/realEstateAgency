package com.real.cyd.service.impl;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.Result;
import com.real.cyd.bean.SysUser;
import com.real.cyd.mapper.SysUserMapper;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.resp.vo.UserRoleInfoVo;
import com.real.cyd.service.UserService;
import com.real.cyd.utils.ToolsUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private SysUserMapper userMapper;

    @Transactional
    public int insertUser(SysUser user) {
        if(user == null){
            return 0;
        }
        if(user.getId() == null || user.getId().equals("")){
            user.setId(UUID.randomUUID().toString());
        }
        if(user.getCreateTime() == null){
            user.setCreateTime(new Date());
        }
        if(user.getState() == null){
            user.setState(0);
        }
        if(user.getPassword()!= null){
            user.setSalt(user.getId());
            Object obj = new SimpleHash("MD5", user.getPassword(), user.getCredentialsSalt(), 10);
            user.setPassword(obj.toString());
        }
        return userMapper.insert(user);
    }

    @Transactional
    public int updateUser(SysUser user) {
        if(user.getState() == null){
            user.setState(0);
        }
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Transactional
    public int deleteUser(SysUser user) {
        return 0;
    }

    public RespBean queryUserList(QueryUserReq user) {
        if(user.getTime()!= null && !user.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(user.getTime());
            user.setStartTime(timeSplit[0]);
            user.setEndTime(timeSplit[1]);
        }

        List<SysUser> sysUsers = userMapper.userList(user);
        int count = userMapper.getCount(user);
        if(sysUsers == null || sysUsers.size() == 0){
            return null;
        }

        for(SysUser sysUser:sysUsers){
            if(sysUser.getCreateTime() != null){
                sysUser.setDateStr(ToolsUtils.getDateStr(sysUser.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(sysUsers,count);
    }
    @Transactional
    @Override
    public RespBean deleteUser(String id) {
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = userMapper.deleteByPrimaryKey(s);
            if(i == 0){
                result++;
            }
        }
        if(result == 0){
            res.setErrorNo("0");
        }
        return res;
    }

    @Override
    public RespBeanOneObj getUserInfo(String id) {
        SysUser sysUser = userMapper.selectByPrimaryKey(id);

        return ToolsUtils.getRespOneObj(sysUser);
    }

    @Override
    public SysUser findByUsername(String username) {
        SysUser user = new SysUser();
        user.setUsername(username);
        user = userMapper.findByUserName(user);
        return user;
    }

    @Override
    public RespBeanOneObj getUserRoleInfo(String id) {
        SysUser user = new SysUser();
        user.setId(id);
        UserRoleInfoVo list = userMapper.getUserRoleInfo(user);
        return ToolsUtils.getRespOneObj(list);
    }
    @Transactional
    @Override
    public void updateUserRole(UserRoleInfoVo vo) {
        //1删除原角色
        userMapper.deleteUserRole(vo);
        //2追加新角色
        vo.setRoleName(UUID.randomUUID().toString());
        userMapper.addUserRole(vo);
    }

}
