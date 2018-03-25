package com.real.cyd.service.impl;

import com.real.cyd.bean.*;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.bean.vo.LdOrderVo;
import com.real.cyd.mapper.*;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.OrderService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 11:13
 **/
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private LdOrderMapper ldOrderMapper;
    @Resource
    private LdOrderInfoMapper ldOrderInfoMapper;
    @Resource
    private LdClientMapper ldClientMapper;
    @Resource
    private LdClothesTypeMapper ldClothesTypeMapper;
    @Resource
    private LdLaundryTypeMapper ldLaundryTypeMapper;
    @Resource
    private LdIntegralMapper ldIntegralMapper;

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

        List<LdOrderVo> list = ldOrderMapper.queryList(req);
        int count = ldOrderMapper.count();
        for(LdOrderVo bean:list){
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
    public RespBean insert(LdOrder bean) {
        RespBean res = new RespBean();
        res.setErrorNo("1");
        if(bean == null){
            res.setErrorInfo("订单为空");
            return res;
        }
        if(ToolsUtils.IsNull(bean.getId())){
            bean.setId(UUID.randomUUID().toString());
        }
        if(bean.getCreateTime() == null){
            bean.setCreateTime(new Date());
        }
        if(ToolsUtils.IsNull(bean.getPhone())){
            res.setErrorInfo("手机号为空");
            return res;
        }
        LdClient client = ldClientMapper.queryClientByPhone(bean);
        if(client == null || ToolsUtils.IsNull(client.getId())){
            res.setErrorInfo("手机号不存在,请先登记");
            return res;
        }
        bean.setClientId(client.getId());
        int i = ldOrderMapper.insertSelective(bean);
        if(i != 1){
            res.setErrorInfo("添加失败");
            return res;
        }
        res.setErrorNo("0");
        return res;
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
            int i = ldOrderMapper.deleteByPrimaryKey(s);
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
        return null;
    }

    @Override
    public void update(LdLaundryType bean) {

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
    public RespBean infoAdd(LdOrderInfo bean) {

        RespBean res = new RespBean();
        res.setErrorNo("1");
        if(bean == null){
            return null;
        }
        if(bean.getSum() == null){
            infoAddFn(bean);
            res.setErrorNo("0");
            return res;
        }
        for(int i = 0; i < bean.getSum(); i++){
            infoAddFn(bean);
        }

        res.setErrorNo("0");
        return res;
    }

    public void infoAddFn(LdOrderInfo bean){

        bean.setId(UUID.randomUUID().toString());

        if(bean.getCreateTime() == null){
            bean.setCreateTime(new Date());
        }
        ldOrderInfoMapper.insertSelective(bean);
        //修改总件数
        ldOrderMapper.updateOrderIdSum(bean);
        //修改用户积分
        LdClothesType ldClothesType = ldClothesTypeMapper.selectByPrimaryKey(bean.getClothestypeId());
        LdLaundryType ldLaundryType = ldLaundryTypeMapper.selectByPrimaryKey(bean.getLaundeytypeId());
        BigDecimal price = ldClothesType.getPrice().add(ldLaundryType.getPrice());
        LdOrder ldOrder = ldOrderMapper.selectByPrimaryKey(bean.getOrderId());
        ldOrder.setPrice(price);
        ldClientMapper.updateLevel(ldOrder);
        //修改总价
        ldOrderMapper.updatePrice(ldOrder);
    }

    @Override
    public RespBeanOneObj getInfoInfo(String id) {
        return ToolsUtils.getRespOneObj(ldOrderInfoMapper.selectByPrimaryKey(id));
    }

    @Override
    public RespBean infoUpdate(LdOrderInfo bean) {
        RespBean res = new RespBean();
        res.setErrorNo("0");
        ldOrderInfoMapper.updateByPrimaryKeySelective(bean);
        return res;
    }

    @Override
    public RespBeanOneObj getEmTime(String orderId) {
        RespBeanOneObj res = new RespBeanOneObj();
        LdOrder ldOrder = ldOrderMapper.selectByPrimaryKey(orderId);
        if(ldOrder == null){
            res.setErrorNo("0");
            return res;
        }
        if(ldOrder.getSum() != null){
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.HOUR, 2 * ldOrder.getSum());// 24小时制
            date = cal.getTime();
            ldOrder.setDateStr(ToolsUtils.getDateStr(date));
        }
        List<LdIntegral> ldIntegrals = ldIntegralMapper.queryListOrder();  //得到正序积分
        //得到该定单的客户
        LdClient client = ldClientMapper.selectByPrimaryKey(ldOrder.getClientId());
        BigDecimal price = ldOrder.getPrice();
        for(LdIntegral integral : ldIntegrals){
            if(integral.getIntegral() <= client.getLevel()){
                client.setIntegralName(integral.getName());
                BigDecimal discount = BigDecimal.valueOf(integral.getDiscount());
                BigDecimal multiply = price.multiply(discount);
                ldOrder.setRealPrice(multiply);
                break;
            }
            if(integral.getIntegral() == ldIntegrals.get(ldIntegrals.size() -1 ).getIntegral()){
                client.setIntegralName(integral.getName());
                BigDecimal discount = BigDecimal.valueOf(integral.getDiscount());
                BigDecimal multiply = price.multiply(discount);
                ldOrder.setRealPrice(multiply);
            }
        }
        //修改真实价格
        ldOrderMapper.updateRealPrice(ldOrder);
        return ToolsUtils.getRespOneObj(ldOrder);
    }

    @Override
    public RespBean complete(LdOrder order) {
        return null;
    }
}