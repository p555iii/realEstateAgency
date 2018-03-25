package com.real.cyd.service.impl;

import com.real.cyd.bean.LdClient;
import com.real.cyd.bean.LdIntegral;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.SysUser;
import com.real.cyd.mapper.LdClientMapper;
import com.real.cyd.mapper.LdIntegralMapper;
import com.real.cyd.req.QueryUserReq;
import com.real.cyd.req.ld.QueryClientReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClientService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-18 15:44
 **/
@Service
public class ClientServiceImpl implements ClientService{

    @Resource
    private LdClientMapper ldClientMapper;
    @Resource
    private LdIntegralMapper ldIntegralMapper;


    public RespBean queryClientList(QueryClientReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }

        List<LdClient> clients = ldClientMapper.clienList(req);
        List<LdIntegral> ldIntegrals = ldIntegralMapper.queryListOrder();
        int count = ldClientMapper.getCount(req);
        if(clients == null || clients.size() == 0){
            return null;
        }

        for(LdClient client:clients){
            if(client.getCreateTime() != null){
                client.setDateStr(ToolsUtils.getDateStr(client.getCreateTime()));
            }
            if(client.getLevel() !=  null ){
                for(LdIntegral integral : ldIntegrals){
                    if(integral.getIntegral() <= client.getLevel()){
                        client.setIntegralName(integral.getName());
                        break;
                    }
                    if(integral.getIntegral() == ldIntegrals.get(ldIntegrals.size() -1 ).getIntegral()){
                        client.setIntegralName(integral.getName());
                    }
                }
            }
        }
        return ToolsUtils.getRespBean(clients,count);
    }
    @Transactional
    @Override
    public void insertClient(LdClient client) {
        if(client == null){
            return;
        }
        if(client.getId() == null || client.getId().equals("")){
            client.setId(UUID.randomUUID().toString());
        }
        if(client.getCreateTime() == null){
            client.setCreateTime(new Date());
        }
        if(client.getLevel() == null || client.getLevel() == 0){
            client.setLevel(1);
        }
        ldClientMapper.insert(client);
    }
    @Transactional
    @Override
    public RespBean deleteClient(String id) {
        if(id == null || id.equals("")) return null;
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = ldClientMapper.deleteByPrimaryKey(s);
            if(i == 0){
                result++;
            }
        }
        if(result == 0){
            res.setErrorNo("0");
        }
        return res;
    }

    @Override
    public RespBeanOneObj getUserInfo(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        LdClient client = ldClientMapper.selectByPrimaryKey(id);
        return ToolsUtils.getRespOneObj(client);
    }

    @Override
    public void updateClient(LdClient client) {
        if(client == null) return;
        if(ToolsUtils.IsNull(client.getId())) return;
        ldClientMapper.updateByPrimaryKeySelective(client);
    }
}