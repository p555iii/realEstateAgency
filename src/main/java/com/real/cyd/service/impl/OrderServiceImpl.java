package com.real.cyd.service.impl;

import com.real.cyd.bean.*;
import com.real.cyd.bean.vo.LdOrderInfoVo;
import com.real.cyd.bean.vo.LdOrderVo;
import com.real.cyd.mapper.*;
import com.real.cyd.req.ld.QueryDetailsReq;
import com.real.cyd.req.ld.QueryOrderReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.FinRecordedService;
import com.real.cyd.service.LdOrderExecutionService;
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
    @Resource
    private FinRecordedService finRecordedService;
    @Resource
    private FinRecordedMapper finRecordedMapper;
    @Resource
    private LdOrderExecutionService ldOrderExecutionService;
    @Resource
    private SysUserMapper sysUserMapper;

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
    public RespBean queryReservationList(QueryOrderReq req) {
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

        List<LdOrderVo> list = ldOrderMapper.queryReservationList(req);
        int count = ldOrderMapper.reservationCount();
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
        System.out.println(bean.getUserId()+"------------");
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
    public RespBean infoDelete(String id,String orderId) {
        LdOrder order = ldOrderMapper.selectByPrimaryKey(orderId);
        LdOrderInfo bean = ldOrderInfoMapper.selectByPrimaryKey(id);
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = ldOrderInfoMapper.deleteByPrimaryKey(s);

            //修改用户积分
            LdClothesType ldClothesType = ldClothesTypeMapper.selectByPrimaryKey(bean.getClothestypeId());
            LdLaundryType ldLaundryType = ldLaundryTypeMapper.selectByPrimaryKey(bean.getLaundeytypeId());
            BigDecimal price = ldClothesType.getPrice().add(ldLaundryType.getPrice());
            order.setSum(order.getSum() - 1);
            order.setPrice(order.getPrice().subtract(price));
            ldOrderMapper.updateByPrimaryKeySelective(order);

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
    public RespBean deleteReservation(String id) {
        if(ToolsUtils.IsNull(id)){
            return null;
        }
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = ldOrderMapper.deleteReservation(s);
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
    public RespBean reservationDetails(QueryDetailsReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }
        List<LdOrderInfoVo> list = ldOrderInfoMapper.reservationList(req);
        System.out.println(req.getId());
        int count = ldOrderInfoMapper.reservationCount(req);
        for(LdOrderInfoVo bean : list){
            if(bean.getCreateTime() != null){
                bean.setDateStr(ToolsUtils.getDateStr(bean.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(list,count);
    }

    @Override
    @Transactional
    public RespBean toOrder(String id,String username) {

        RespBean res = new RespBean();
        res.setErrorNo("1");

        if(ToolsUtils.IsNull(id)){
            res.setErrorInfo("id为空");
            return  res;
        }
        LdOrder ldOrder = ldOrderMapper.selectByPrimaryKey(id);
        if(ldOrder != null && !ldOrder.getId().equals("")){
            res.setErrorInfo("该预定已经提交无需再次提交");
            return res;
        }
        LdOrder order = ldOrderMapper.selectByPrimaryKeyRe(id); //查预定订单
        if(order==null || order.getId().equals("")){
            res.setErrorInfo("该预定不存在");
            return res;
        }
        QueryDetailsReq req = new QueryDetailsReq();
        req.setOrderId(id);
        List<LdOrderInfo> list = ldOrderInfoMapper.reservationLists(req);
        if(list==null || list.size() == 0 ){
            res.setErrorInfo("该预定无订购");
            return res;
        }
        order.setUserId(username);
        //添加到订单表
        ldOrderMapper.insertSelective(order);
        for(LdOrderInfo bean : list){
            ldOrderInfoMapper.insertSelective(bean);
            //ldOrderInfoMapper.deleteByPrimaryKeyRe(bean);
        }
        //ldOrderMapper.deleteReservation(id);
        res.setErrorNo("0");
        return res;
    }
    @Transactional
    @Override
    public LdOrder addReservation(String phone) {
        LdOrder order = new LdOrder();
        order.setPhone(phone);
        LdClient client = ldClientMapper.queryClientByPhone(order);
        order.setClientId(client.getId());
        order.setId(UUID.randomUUID().toString());
        order.setCreateTime(new Date());
        ldOrderMapper.addReservation(order);
        return order;
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
    @Transactional
    public void infoAddFn(LdOrderInfo bean){

        bean.setId(UUID.randomUUID().toString());

        if(bean.getCreateTime() == null){
            bean.setCreateTime(new Date());
        }
        bean.setState(0);
        ldOrderInfoMapper.insertSelective(bean);
        //修改总件数
        ldOrderMapper.updateOrderIdSum(bean);
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
    @Transactional
    public void addReservationInfo(LdOrderInfo orderInfo) {
        for(int i = 0; i < orderInfo.getSum(); i++){
            orderInfo.setId(UUID.randomUUID().toString());
            orderInfo.setState(0);
            orderInfo.setCreateTime(new Date());
            ldOrderInfoMapper.addReservationInfo(orderInfo);
            ldOrderMapper.updateReservationSum(orderInfo);
            //修改用户积分
            LdClothesType ldClothesType = ldClothesTypeMapper.selectByPrimaryKey(orderInfo.getClothestypeId());
            LdLaundryType ldLaundryType = ldLaundryTypeMapper.selectByPrimaryKey(orderInfo.getLaundeytypeId());
            BigDecimal price = ldClothesType.getPrice().add(ldLaundryType.getPrice());
            LdOrder ldOrder = new LdOrder();
            ldOrder.setId(orderInfo.getOrderId());
            ldOrder.setPrice(price);
            //修改总价
            ldOrderMapper.updateReservationPrice(ldOrder);
        }
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
            cal.add(Calendar.HOUR, 24 + 12 * Math.round(ldOrder.getSum() / 10));// 24小时制
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
    //完成订单
    @Override
    public RespBean complete(SysUser user,LdOrder order) {
        //入账务表   默认是洗衣收入
        FinRecorded recorded = new FinRecorded();
        recorded.setMoney(order.getRealPrice());
        recorded.setUserid(user.getId());
        recorded.setClientid(order.getClientId());
        recorded.setRecord(0);
        recorded.setSourceid("5c8a1e64-8efc-445e-a0e0-bb85da1f4c23");
        recorded.setOrderId(order.getId());
        FinRecorded record = finRecordedMapper.queryOrderId(order);
        if(record == null){
            finRecordedService.insert(recorded);
        }else{
            finRecordedService.update(recorded);

        }
        ldOrderMapper.updateEmTime(order);
        order = ldOrderMapper.selectByPrimaryKey(order.getId());
        //添加到洗衣处理表
        LdOrderexecution bean = new LdOrderexecution();
        bean.setId(order.getId());
        bean.setEstimatedTime(order.getEstimatedTime());
        bean.setClientId(order.getClientId());
        bean.setSum(order.getSum());
        ldOrderExecutionService.insert(bean);
        RespBean res = new RespBean();
        res.setErrorNo("0");
        return res;
    }


}