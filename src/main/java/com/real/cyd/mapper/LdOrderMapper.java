package com.real.cyd.mapper;

import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.LdOrder;
import com.real.cyd.bean.LdOrderInfo;
import com.real.cyd.bean.vo.LdOrderVo;
import com.real.cyd.req.ld.QueryOrderReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LdOrderMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdOrder record);

    int insertSelective(LdOrder record);

    LdOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LdOrder record);

    int updateByPrimaryKey(LdOrder record);

    List<LdOrderVo> queryList(QueryOrderReq req);

    int count();

    int updateOrderIdSum(LdOrderInfo bean);

    void updatePrice(LdOrder ldOrder);
}