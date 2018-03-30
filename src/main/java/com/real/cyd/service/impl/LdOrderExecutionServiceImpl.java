package com.real.cyd.service.impl;

import com.real.cyd.bean.LdOrderexecution;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.bean.vo.OrderExecutionVo;
import com.real.cyd.mapper.LdOrderInfoMapper;
import com.real.cyd.mapper.LdOrderexecutionMapper;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.LdOrderExecutionService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-29 12:01
 **/
@Service
public class LdOrderExecutionServiceImpl implements LdOrderExecutionService {
    @Resource
    private LdOrderexecutionMapper ldOrderexecutionMapper;
    @Resource
    private LdOrderInfoMapper ldOrderInfoMapper;
    @Override
    public RespBean queryList(QueryOrderReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }
        if(req.getEmTime()!= null && !req.getEmTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getEmTime());
            req.setEmStartTime(timeSplit[0]);
            req.setEmEndTime(timeSplit[1]);
        }

        List<OrderExecutionVo> list = ldOrderexecutionMapper.queryList(req);
        int count = ldOrderexecutionMapper.count();
        for(OrderExecutionVo bean:list){
            if(bean.getCreateTime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreateTime()));
            }
            if(bean.getEstimatedTime() != null){
                bean.setEmDateStr(ToolsUtils.getDateStr(bean.getEstimatedTime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }


    @Override
    public void insert(LdOrderexecution bean) {
        if(bean == null){
            return;
        }
        if(bean.getCreateTime() == null){
            bean.setCreateTime(new Date());
        }
        bean.setState(0);
        bean.setIsTakeAway(0);
        ldOrderexecutionMapper.insertSelective(bean);
    }

    @Override
    public RespBean delete(String id) {
        RespBean res = new RespBean();
        res.setErrorNo("0");
        if(ToolsUtils.IsNull(id)){
            res.setErrorNo("1");
            res.setErrorInfo("id为空");
            return res;
        }
        ldOrderexecutionMapper.deleteByPrimaryKey(id);
        return res;
    }

    @Override
    public RespBeanOneObj getInfo(String id) {
        if(ToolsUtils.IsNull(id)){
            RespBeanOneObj respBeanOneObj = new RespBeanOneObj();
            respBeanOneObj.setErrorNo("0");
            return respBeanOneObj;
        }
        return ToolsUtils.getRespOneObj(ldOrderexecutionMapper.selectByPrimaryKey(id));
    }

    @Override
    public void update(LdOrderexecution bean) {
        ldOrderexecutionMapper.updateByPrimaryKeySelective(bean);
    }

    @Override
    public RespBean details(QueryDetailsReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }
        List<LdOrderInfoVo> list = ldOrderInfoMapper.list(req);
        System.out.println(req.getId());
        int count = ldOrderInfoMapper.count(req);
        for(LdOrderInfoVo bean : list){
            if(bean.getCreateTime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    @Transactional
    public RespBean complete(String infoId, String orderId) {
        RespBean res = new RespBean();
        res.setErrorNo("1");
        if(ToolsUtils.IsNull(infoId) || ToolsUtils.IsNull(orderId)){
            res.setErrorInfo("id为空");
            return res;
        }
        ldOrderInfoMapper.completeLaunry(infoId);
        //完成后判断当前完成数是否和总数一致 如一致 改变主表的状态
        LdOrderexecution ldOrderexecution = ldOrderexecutionMapper.selectByPrimaryKey(orderId);
        int count = ldOrderInfoMapper.getCompleteSum(orderId);
        if(ldOrderexecution != null && ldOrderexecution.getSum() != null){
            if(ldOrderexecution.getSum() == count){
                LdOrderexecution order = new LdOrderexecution();
                order.setState(1);
                order.setId(orderId);
                int i = ldOrderexecutionMapper.updateByPrimaryKeySelective(order);
            }
        }
        res.setErrorNo("0");
        return res;
    }

    @Override
    @Transactional
    public RespBean completeOrder(String id) {
        RespBean res = new RespBean();
        res.setErrorNo("1");
        if(ToolsUtils.IsNull(id)){
            res.setErrorInfo("id为空");
            return res;
        }
        LdOrderexecution ldOrderexecution = ldOrderexecutionMapper.selectByPrimaryKey(id);
        if(ldOrderexecution!=null && ldOrderexecution.getState() == 1){
            res.setErrorInfo("该订单已经完成了  不需要再次完成");
            return res;
        }
        ldOrderexecution.setState(1);
        ldOrderexecutionMapper.updateByPrimaryKeySelective(ldOrderexecution);
        ldOrderInfoMapper.completeLaunryByOrderId(id);
        res.setErrorNo("0");
        return res;
    }

    @Override
    @Transactional
    public RespBean outbound(String id) {
        RespBean res = new RespBean();
        res.setErrorNo("1");
        if(ToolsUtils.IsNull(id)){
            res.setErrorInfo("id为空");
            return res;
        }
        LdOrderexecution ldOrderexecution = ldOrderexecutionMapper.selectByPrimaryKey(id);
        if(ldOrderexecution == null){
            res.setErrorInfo("不存在该订单");
            return res;
        }
        if(ldOrderexecution!=null && ldOrderexecution.getState() == 0){
            res.setErrorInfo("该订单还未完成");
            return res;
        }
        if(ldOrderexecution!=null && ldOrderexecution.getIsTakeAway()== 1){
            res.setErrorInfo("该订单已出库");
            return res;
        }
        ldOrderexecution.setIsTakeAway(1);
        ldOrderexecutionMapper.updateByPrimaryKeySelective(ldOrderexecution);
        res.setErrorNo("0");
        return res;
    }
}