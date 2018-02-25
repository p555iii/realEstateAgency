package com.real.cyd.service.impl;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.Result;
import com.real.cyd.bean.SysUser;
import com.real.cyd.mapper.SysUserMapper;
import com.real.cyd.service.UserService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
        return userMapper.insert(user);
    }


    public int updateUser(SysUser user) {
        return 0;
    }


    public int deleteUser(SysUser user) {
        return 0;
    }

    public RespBean queryUserList() {

        List<SysUser> sysUsers = userMapper.userList();
        int count = userMapper.getCount();
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
}
