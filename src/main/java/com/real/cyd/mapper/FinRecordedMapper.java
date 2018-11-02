package com.real.cyd.mapper;

import com.real.cyd.bean.FinRecorded;
import com.real.cyd.bean.LdOrder;
import com.real.cyd.bean.vo.FinRecordedVo;
import com.real.cyd.req.fin.FinRecordReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
@Mapper
public interface FinRecordedMapper {


    int deleteByPrimaryKey(String id);

    int insert(FinRecorded record);

    int insertSelective(FinRecorded record);

    FinRecorded selectByPrimaryKey(String id);


    int updateByPrimaryKeySelective(FinRecorded record);

    int updateByPrimaryKeyWithBLOBs(FinRecorded record);

    int updateByPrimaryKey(FinRecorded record);

    List<FinRecordedVo> queryList(FinRecordReq record);

    int count(FinRecordReq record);

    double toDayRecord();

    double toDayDebit();

    BigDecimal countRecord(@Param("startTime")String startTime,@Param("endTime")String endTime);

    FinRecorded queryOrderId(LdOrder order);

    BigDecimal getRecordSum();

    BigDecimal getGiveSum();

    BigDecimal getSumThisMonthRecord(@Param("month") int i);

    BigDecimal getSumThisMonthGive(@Param("month") int i);
}