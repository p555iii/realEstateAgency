package com.real.cyd.mapper;

import com.real.cyd.bean.FinRecorded;
import com.real.cyd.bean.vo.FinRecordedVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    List<FinRecordedVo> queryList();

    int count();
}