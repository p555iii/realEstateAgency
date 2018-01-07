package com.real.cyd.service;

import com.real.cyd.bean.User;

import java.util.List;

public interface UserService {
    public int insertUser(User user);
    public int updateUser(User user);
    public int deleteUser(User user);
    public List<User> queryUserList();
}
