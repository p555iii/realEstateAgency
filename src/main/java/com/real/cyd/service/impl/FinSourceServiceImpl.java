package com.real.cyd.service.impl;

import com.real.cyd.bean.FinSource;
import com.real.cyd.bean.RespBean;
import com.real.cyd.mapper.FinSourceMapper;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.FinSourceService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 19:50
 **/
@Service
public class FinSourceServiceImpl implements FinSourceService{
    @Resource
    private FinSourceMapper finSourceMapper;
    @Override
    public RespBean queryList() {
        List<FinSource> list = finSourceMapper.queryList();
        int count = finSourceMapper.count();
        for(FinSource bean:list){
            if(bean.getCreatetime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreatetime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    public void insert(FinSource bean) {
        if(bean == null){
            return;
        }
        if(ToolsUtils.IsNull(bean.getId())){
            bean.setId(UUID.randomUUID().toString());
        }
        if(bean.getCreatetime() == null){
            bean.setCreatetime(new Date());
        }
        finSourceMapper.insertSelective(bean);
    }

    @Override
    public RespBean delete(String id) {
        RespBean res = new RespBean();
        res.setErrorNo("0");
        if(ToolsUtils.IsNull(id)){
            return res;
        }
        finSourceMapper.deleteByPrimaryKey(id);
        return res;
    }

    @Override
    public RespBeanOneObj getInfo(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        return ToolsUtils.getRespOneObj(finSourceMapper.selectByPrimaryKey(id));
    }

    @Override
    public void update(FinSource bean) {
        finSourceMapper.updateByPrimaryKeySelective(bean);
    }
}