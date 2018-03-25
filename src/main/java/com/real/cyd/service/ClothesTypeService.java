package com.real.cyd.service;

import com.real.cyd.bean.LdClothesType;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-19 14:13
 **/
public interface ClothesTypeService {
    RespBean queryClothesTypeList();

    RespBean deleteClothesType(String id);

    RespBeanOneObj getClothesTypeInfo(String id);

    void updateClothesType(LdClothesType clothesType);

    void insertClothesType(LdClothesType clothesType);

    RespBeanOneObj clothList();
}
