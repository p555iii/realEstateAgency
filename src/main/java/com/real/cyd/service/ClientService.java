package com.real.cyd.service;

import com.real.cyd.bean.LdClient;
import com.real.cyd.bean.RespBean;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.req.ld.QueryClientReq;
import com.real.cyd.resp.RespBeanOneObj;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-18 15:44
 **/
public interface ClientService {
    RespBean queryClientList(QueryClientReq req);

    RespBean insertClient(LdClient client);

    RespBean deleteClient(String id);

    RespBeanOneObj getUserInfo(String id);

    void updateClient(LdClient client);


}
