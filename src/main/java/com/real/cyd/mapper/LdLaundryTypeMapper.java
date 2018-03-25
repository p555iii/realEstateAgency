package com.real.cyd.mapper;

import com.real.cyd.bean.LdLaundryType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LdLaundryTypeMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdLaundryType record);

    int insertSelective(LdLaundryType record);


    LdLaundryType selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(LdLaundryType record);

    int updateByPrimaryKey(LdLaundryType record);

    List<LdLaundryType> queryList();

    int count();
}