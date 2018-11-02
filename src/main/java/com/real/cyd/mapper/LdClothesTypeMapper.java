package com.real.cyd.mapper;

import com.real.cyd.bean.LdClothesType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LdClothesTypeMapper {


    int deleteByPrimaryKey(String id);

    int insert(LdClothesType record);

    int insertSelective(LdClothesType record);



    LdClothesType selectByPrimaryKey(String id);



    int updateByPrimaryKeySelective(LdClothesType record);

    int updateByPrimaryKey(LdClothesType record);

    List<LdClothesType> queryList();

    int count();

    List<LdClothesType> list();
}