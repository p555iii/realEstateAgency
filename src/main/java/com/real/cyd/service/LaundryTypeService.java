package com.real.cyd.service;

import com.real.cyd.bean.LdClothesType;
import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;
import org.springframework.stereotype.Service;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-19 15:22
 **/

public interface LaundryTypeService {
    RespBean queryList();

    void insert(LdLaundryType laundryType);

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(LdLaundryType laundryType);

    RespBeanOneObj laundryList();
}