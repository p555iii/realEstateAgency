package com.real.cyd.service.impl;

import com.real.cyd.bean.LdClothesType;
import com.real.cyd.bean.RespBean;
import com.real.cyd.mapper.LdClothesTypeMapper;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClothesTypeService;
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
 * @create: 2018-03-19 14:13
 **/
@Service
public class ClothesTypeServiceImpl implements ClothesTypeService {

    @Resource
    private LdClothesTypeMapper ldClothesTypeMapper;

    @Override
    public RespBean queryClothesTypeList() {
        List<LdClothesType> list = ldClothesTypeMapper.queryList();
        int count = ldClothesTypeMapper.count();
        for(LdClothesType sysUser:list){
            if(sysUser.getCreateTime() != null){
                sysUser.setDateStr(ToolsUtils.getDateStr(sysUser.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    @Transactional
    public RespBean deleteClothesType(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = ldClothesTypeMapper.deleteByPrimaryKey(s);
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
    public RespBeanOneObj getClothesTypeInfo(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        return ToolsUtils.getRespOneObj(ldClothesTypeMapper.selectByPrimaryKey(id));
    }

    @Override
    @Transactional
    public void updateClothesType(LdClothesType clothesType) {
        if(clothesType == null){
            return;
        }
        if(ToolsUtils.IsNull(clothesType.getId())){
            return;
        }
        ldClothesTypeMapper.updateByPrimaryKeySelective(clothesType);
    }

    @Override
    @Transactional
    public void insertClothesType(LdClothesType clothesType) {
        if(clothesType == null){
            return;
        }
        if(ToolsUtils.IsNull(clothesType.getId())){
            clothesType.setId(UUID.randomUUID().toString());
        }
        if(clothesType.getCreateTime() == null){
            clothesType.setCreateTime(new Date());
        }
        ldClothesTypeMapper.insertSelective(clothesType);
    }

    @Override
    public RespBeanOneObj clothList() {
        return ToolsUtils.getRespOneObj(ldClothesTypeMapper.queryList());
    }
}