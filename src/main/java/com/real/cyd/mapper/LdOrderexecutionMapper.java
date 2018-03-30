package com.real.cyd.mapper;

import com.real.cyd.bean.LdOrderexecution;
import com.real.cyd.bean.vo.OrderExecutionVo;
import com.real.cyd.req.ld.QueryOrderReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LdOrderexecutionMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdOrderexecution record);

    int insertSelective(LdOrderexecution record);


    LdOrderexecution selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LdOrderexecution record);

    int updateByPrimaryKey(LdOrderexecution record);

    List<OrderExecutionVo> queryList(QueryOrderReq req);

    int count();

}