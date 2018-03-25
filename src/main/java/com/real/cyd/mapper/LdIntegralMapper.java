package com.real.cyd.mapper;

import com.real.cyd.bean.LdIntegral;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LdIntegralMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdIntegral record);

    int insertSelective(LdIntegral record);


    LdIntegral selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LdIntegral record);

    int updateByPrimaryKey(LdIntegral record);

    List<LdIntegral> queryList();

    int count();

    List<LdIntegral> queryListOrder();

    List<LdIntegral> queryListOrderAsc();
}