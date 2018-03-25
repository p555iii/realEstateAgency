package com.real.cyd.mapper;

import com.real.cyd.bean.FinSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface FinSourceMapper {


    int deleteByPrimaryKey(String id);

    int insert(FinSource record);

    int insertSelective(FinSource record);

    FinSource selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(FinSource record);

    int updateByPrimaryKey(FinSource record);

    List<FinSource> queryList();

    int count();
}