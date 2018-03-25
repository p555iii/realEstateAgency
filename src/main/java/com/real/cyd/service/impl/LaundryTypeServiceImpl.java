package com.real.cyd.service.impl;

import com.real.cyd.bean.LdLaundryType;
import com.real.cyd.bean.RespBean;
import com.real.cyd.mapper.LdLaundryTypeMapper;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.LaundryTypeService;
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
 * @create: 2018-03-19 15:23
 **/
@Service
public class LaundryTypeServiceImpl implements LaundryTypeService {

    @Resource
    private LdLaundryTypeMapper ldLaundryTypeMapper;

    @Override
    public RespBean queryList() {
        List<LdLaundryType> list = ldLaundryTypeMapper.queryList();
        int count = ldLaundryTypeMapper.count();
        for(LdLaundryType bean:list){
            if(bean.getCreateTime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    public void insert(LdLaundryType laundryType) {
        if(laundryType == null){
            return;
        }
        if(ToolsUtils.IsNull(laundryType.getId())){
            laundryType.setId(UUID.randomUUID().toString());
        }
        if(laundryType.getCreateTime() == null){
            laundryType.setCreateTime(new Date());
        }
        ldLaundryTypeMapper.insertSelective(laundryType);
    }

    @Override
    public RespBean delete(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = ldLaundryTypeMapper.deleteByPrimaryKey(s);
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
    public RespBeanOneObj getInfo(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        return ToolsUtils.getRespOneObj(ldLaundryTypeMapper.selectByPrimaryKey(id));
    }

    @Override
    public void update(LdLaundryType laundryType) {
        if(laundryType == null){
            return;
        }
        if(ToolsUtils.IsNull(laundryType.getId())){
            return;
        }
        ldLaundryTypeMapper.updateByPrimaryKeySelective(laundryType);
    }

    @Override
    public RespBeanOneObj laundryList() {
        return ToolsUtils.getRespOneObj(ldLaundryTypeMapper.queryList());
    }
}