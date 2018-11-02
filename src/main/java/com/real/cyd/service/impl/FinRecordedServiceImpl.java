package com.real.cyd.service.impl;

import com.real.cyd.bean.FinRecorded;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.vo.FinRecordedVo;
import com.real.cyd.mapper.FinRecordedMapper;
import com.real.cyd.req.fin.FinRecordReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.FinRecordedService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 20:53
 **/
@Service
public class FinRecordedServiceImpl implements FinRecordedService{
    @Resource
    private FinRecordedMapper finRecordedMapper;

    @Override
    public RespBean queryList(FinRecordReq record) {
        if(record.getTime()!= null && !record.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(record.getTime());
            record.setStartTime(timeSplit[0]);
            record.setEndTime(timeSplit[1]);
        }

        List<FinRecordedVo> list = finRecordedMapper.queryList(record);
        int count = finRecordedMapper.count(record);
        for(FinRecordedVo bean:list){
            if(bean.getCreatetime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreatetime()));
            }
            if(bean.getRecord() != null && bean.getMoney() != null){
                if(bean.getRecord() == 0){  //收入
                    bean.setMoneyStr("+"+bean.getMoney().toString());
                }else if(bean.getRecord() == 1){ //支出
                    bean.setMoneyStr("-"+bean.getMoney().toString());
                }
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    public void insert(FinRecorded bean) {
        if(bean == null){
            return;
        }
        if(ToolsUtils.IsNull(bean.getId())){
            bean.setId(UUID.randomUUID().toString());
        }
        if(bean.getCreatetime() == null){
            Date createTime = new Date();
            bean.setCreatetime(createTime);
            Calendar c = Calendar.getInstance();
            if(createTime==null){
                createTime = new Date();
            }
            c.setTime(createTime);
            bean.setYear(createTime.getYear()+1900);
            bean.setMonth(createTime.getMonth()+1);
            bean.setDay(createTime.getDate());
        }
        finRecordedMapper.insertSelective(bean);
    }

    @Override
    public RespBean delete(String id) {
        RespBean res = new RespBean();
        res.setErrorNo("0");
        if(ToolsUtils.IsNull(id)){
            return res;
        }
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = finRecordedMapper.deleteByPrimaryKey(s);
            if(i == 0){
                result++;
            }
        }
        return res;
    }

    @Override
    public RespBeanOneObj getInfo(String id) {
        RespBeanOneObj res = new RespBeanOneObj();
        res.setErrorNo("0");
        if(ToolsUtils.IsNull(id)){
            return res;
        }
        return ToolsUtils.getRespOneObj(finRecordedMapper.selectByPrimaryKey(id));
    }

    @Override
    public void update(FinRecorded bean) {
        finRecordedMapper.updateByPrimaryKeySelective(bean);
    }
}