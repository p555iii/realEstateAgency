package com.real.cyd.service;

import com.real.cyd.bean.FinSource;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 19:50
 **/
public interface FinSourceService {
    RespBean queryList();

    void insert(FinSource bean);

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(FinSource bean);
}
