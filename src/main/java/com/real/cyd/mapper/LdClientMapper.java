package com.real.cyd.mapper;

import com.real.cyd.bean.LdClient;
import com.real.cyd.bean.LdOrder;
import com.real.cyd.req.ld.QueryClientReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface LdClientMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdClient record);

    int insertSelective(LdClient record);

    LdClient selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(LdClient record);

    int updateByPrimaryKey(LdClient record);

    List<LdClient> clienList(QueryClientReq req);

    int getCount(QueryClientReq req);

    LdClient queryClientByPhone(LdOrder bean);

    void updateLevel(LdOrder ldOrder);

}