package com.real.cyd.service.impl;

import com.real.cyd.bean.User;
import com.real.cyd.mapper.UserMapper;
import com.real.cyd.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;

    @Transactional
    public int insertUser(User user) {
        if(user == null){
            return 0;
        }
        if(user.getUserId() == null || user.getUserId().equals("")){
            user.setUserId(UUID.randomUUID().toString());
        }
        return userMapper.insertUser(user);
    }

    public int updateUser(User user) {
        return 0;
    }

    public int deleteUser(User user) {
        return 0;
    }

    public List<User> queryUserList() {
        return userMapper.userList();
    }
}
