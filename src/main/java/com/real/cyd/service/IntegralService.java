package com.real.cyd.service;

import com.real.cyd.bean.LdIntegral;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-21 15:38
 **/
public interface IntegralService {
    RespBean queryList();

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(LdIntegral bean);

    void insert(LdIntegral bean);
}
