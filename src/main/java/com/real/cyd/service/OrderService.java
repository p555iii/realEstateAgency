package com.real.cyd.service;

import com.real.cyd.bean.*;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 11:13
 **/
public interface OrderService {
    RespBean queryList(QueryOrderReq req);

    RespBean insert(LdOrder bean);

    RespBean delete(String id);

    RespBeanOneObj getInfo(String id);

    void update(LdLaundryType bean);

    RespBean details(QueryDetailsReq req);

    RespBean infoAdd(LdOrderInfo bean);

    RespBeanOneObj getInfoInfo(String id);

    RespBean infoUpdate(LdOrderInfo bean);

    RespBeanOneObj getEmTime(String orderId);

    RespBean complete(SysUser user,LdOrder order);

    RespBean queryReservationList(QueryOrderReq req);

    RespBean deleteReservation(String id);

    RespBean reservationDetails(QueryDetailsReq req);

    RespBean toOrder(String id,String username);

    LdOrder addReservation(String phone);

    void addReservationInfo(LdOrderInfo orderInfo);

    RespBean infoDelete(String id,String orderId);
}
