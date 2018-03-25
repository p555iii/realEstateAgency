package com.real.cyd.service;

import com.real.cyd.bean.FinRecorded;
import com.real.cyd.bean.RespBean;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 20:53
 **/
public interface FinRecordedService {
    RespBean queryList();

    void insert(FinRecorded bean);

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(FinRecorded bean);
}
