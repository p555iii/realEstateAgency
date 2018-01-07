package com.real.cyd.mapper;

import com.real.cyd.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Create in INtelliJ IDEA
 * Author cyd
 * Date   2018/1/2
 */
@Component
public interface UserMapper {
    @Select("select * from user")
    public List<User> userList();

    @Insert("insert into user(userId,username,password,nickName) values(#{userId},#{userName},#{password},#{nickName})")
    public int insertUser(User user);
}
