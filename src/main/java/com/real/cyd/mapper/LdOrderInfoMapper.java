package com.real.cyd.mapper;

import com.real.cyd.bean.LdOrderInfo;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.req.ld.QueryDetailsReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LdOrderInfoMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdOrderInfo record);

    int insertSelective(LdOrderInfo record);

    LdOrderInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LdOrderInfo record);

    int updateByPrimaryKey(LdOrderInfo record);

    List<LdOrderInfoVo> list(QueryDetailsReq req);

    int count(QueryDetailsReq req);
}