package com.real.cyd.service;

import com.real.cyd.bean.LdOrderexecution;
import com.real.cyd.bean.RespBean;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-29 12:01
 **/
public interface LdOrderExecutionService {
    RespBean queryList(QueryOrderReq req);

    void insert(LdOrderexecution bean);

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(LdOrderexecution bean);

    RespBean details(QueryDetailsReq req);

    RespBean complete(String infoId, String orderId);

    RespBean completeOrder(String id);

    RespBean outbound(String id);
}
