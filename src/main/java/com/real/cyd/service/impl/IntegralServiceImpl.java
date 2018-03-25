package com.real.cyd.service.impl;

import com.real.cyd.bean.LdIntegral;
import com.real.cyd.bean.RespBean;
import com.real.cyd.mapper.LdIntegralMapper;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.IntegralService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-21 15:38
 **/
@Service
public class IntegralServiceImpl implements IntegralService {
    @Resource
    private LdIntegralMapper ldIntegralMapper;

    @Override
    public RespBean queryList() {
        List<LdIntegral> list = ldIntegralMapper.queryList();
        int count = ldIntegralMapper.count();
        for(LdIntegral bean : list){
            if (bean.getCreateTime() == null) continue;
            bean.setDateStr(ToolsUtils.getDateStr(bean.getCreateTime()));
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    public RespBean delete(String id) {
        if(ToolsUtils.IsNull(id)){return null;}
        RespBean res = new RespBean();
        res.setErrorNo("0");
        ldIntegralMapper.deleteByPrimaryKey(id);
        return res;
    }

    @Override
    public RespBeanOneObj getInfo(String id) {
        if(ToolsUtils.IsNull(id)){return null;}
        return ToolsUtils.getRespOneObj(ldIntegralMapper.selectByPrimaryKey(id));
    }

    @Override
    public void update(LdIntegral bean) {
        if(bean == null ){
            return;
        }
        ldIntegralMapper.updateByPrimaryKeySelective(bean);
    }

    @Override
    public void insert(LdIntegral bean) {
        if(bean == null ){
            return;
        }
        if(ToolsUtils.IsNull(bean.getId())){
            bean.setId(UUID.randomUUID().toString());
        }
        if(bean.getCreateTime() == null){
            bean.setCreateTime(new Date());
        }
        ldIntegralMapper.insertSelective(bean);
    }
}