package com.real.cyd.mapper;

import com.real.cyd.bean.LdOrderInfo;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.req.ld.QueryDetailsReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    int toDayLaundry();

    void completeLaunry(@Param("id") String infoId);

    int getCompleteSum(@Param("orderId")String orderId);

    void completeLaunryByOrderId(@Param("orderId")String id);

    List<LdOrderInfoVo> reservationList(QueryDetailsReq req);

    int reservationCount(QueryDetailsReq req);

    List<LdOrderInfo> reservationLists(QueryDetailsReq req);

    void deleteByPrimaryKeyRe(LdOrderInfo bean);

    void addReservationInfo(LdOrderInfo orderInfo);
}