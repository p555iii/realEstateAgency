package com.real.cyd.controller.sys;

import com.alibaba.fastjson.JSON;
import com.real.cyd.bean.FinRecorded;
import com.real.cyd.bean.FinSource;
import com.real.cyd.bean.LdClient;
import com.real.cyd.bean.vo.RecordBean;
import com.real.cyd.mapper.FinRecordedMapper;
import com.real.cyd.mapper.FinSourceMapper;
import com.real.cyd.mapper.LdOrderInfoMapper;
import com.real.cyd.mapper.LdOrderMapper;
import com.real.cyd.resp.EChartResp;
import com.real.cyd.resp.ResMainBean;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.ClientService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: 用作基本数据的处理
 * @author: cyd
 * @create: 2018-03-25 20:14
 **/
@Controller
public class BaseController {
    @Resource
    private LdOrderMapper ldOrderMapper;
    @Resource
    private FinRecordedMapper finRecordedMapper;
    @Resource
    private LdOrderInfoMapper ldOrderInfoMapper;
    @Resource
    private FinSourceMapper finSourceMapper;

    @Resource
    private ClientService clientService;
    @RequestMapping("/record")
    @ResponseBody
    public RespBeanOneObj record(){
        List<RecordBean> list = new ArrayList<>();
        list.add(new RecordBean("收入",0));
        list.add(new RecordBean("支出",1));
        return ToolsUtils.getRespOneObj(list);
    }
    @RequestMapping("/source")
    @ResponseBody
    public RespBeanOneObj source(String record){
        List<FinSource> list = finSourceMapper.list(record);
        return ToolsUtils.getRespOneObj(list);
    }

    @RequestMapping("/main")
    public String main(Model model,String time){
        ResMainBean bean = new ResMainBean();
        String[] timeSplit;
        if(time != null && !time.equals("")){
            timeSplit = ToolsUtils.getTimeSplit(time);
        }else {
            timeSplit = new String[2];
        }
        int countOrder = ldOrderMapper.queryOrderSum(timeSplit[0],timeSplit[1]);
        bean.setCountOrder(countOrder);
        int toDayOrder = ldOrderMapper.queryOrderSumToDay();
        bean.setToDayOrder(toDayOrder);
        double toDatRecord = finRecordedMapper.toDayRecord();
        bean.setToDayRecord(toDatRecord);
        double toDayDebit = finRecordedMapper.toDayDebit();
        bean.setToDayDebit(toDayDebit);
        BigDecimal countRecord = finRecordedMapper.countRecord(timeSplit[0],timeSplit[1]);
        bean.setCountRecord(0);
        if(countRecord != null){
            bean.setCountRecord(countRecord.doubleValue());
        }

        int toDayLaundry = ldOrderInfoMapper.toDayLaundry();
        bean.setToDayLaundry(toDayLaundry);
        model.addAttribute("bean",bean);
        //当月订单
        EChartResp res = new EChartResp();
        res.setMonth(getCurrentMonth());
        int maxDay = getCurrentMonthLastDay();
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();
        for(int i = 1; i <= maxDay; i++){
            //查当天订单数
            int count = ldOrderMapper.getThisDayOrderCount(i);
            sb1.append(i+",");
            sb2.append(count+",");
        }
        sb1.deleteCharAt(sb1.length() - 1);
        sb2.deleteCharAt(sb2.length() - 1);
        res.setRow(sb1.toString());
        res.setLine(sb2.toString());
        model.addAttribute("data",res);

        //洗衣数
        StringBuffer sb3 = new StringBuffer();
        for(int i = 1; i <= maxDay; i++){
            //查当天订单中的衣物数
            int count = ldOrderMapper.getThisDayLaunryCount(i);
            sb3.append(count+",");
        }
        sb3.deleteCharAt(sb3.length() - 1);
        EChartResp res2 = new EChartResp();
        res2.setMonth(getCurrentMonth());
        res2.setRow(sb1.toString());
        res2.setLine(sb3.toString());
        model.addAttribute("data2",res2);

        //收入
        StringBuffer sb4 = new StringBuffer();
        for(int i = 1; i <= maxDay; i++){
            //查当天收入
            int count = ldOrderMapper.getThisDayPriceCount(i);
            sb4.append(count+",");
        }
        sb4.deleteCharAt(sb4.length() - 1);
        EChartResp res3 = new EChartResp();
        res3.setMonth(getCurrentMonth());
        res3.setRow(sb1.toString());
        res3.setLine(sb4.toString());
        model.addAttribute("data3",res3);

        //收支
        //总收入
        BigDecimal record = finRecordedMapper.getRecordSum();
        //总支出
        BigDecimal f = finRecordedMapper.getGiveSum();
        model.addAttribute("record",record);
        model.addAttribute("f",f);
        StringBuffer sb5 = new StringBuffer();
        StringBuffer sb6 = new StringBuffer();
        //按月份收支
        for(int i = 1; i <= 12; i++){
            //当月收入
            BigDecimal a = finRecordedMapper.getSumThisMonthRecord(i);
            sb5.append(a+",");
            //当月支出
            BigDecimal b = finRecordedMapper.getSumThisMonthGive(i);
            sb6.append(b+",");
        }
        sb5.deleteCharAt(sb5.length() - 1);
        sb6.deleteCharAt(sb6.length() - 1);
        model.addAttribute("a",sb5.toString());
        model.addAttribute("b",sb6.toString());
        return "main";
    }

    @RequestMapping("/laundryCompleteSum")
    public String laundryCompleteSum(Model model){
        //这里查的是订单已经洗衣完成，但是还未被提交和出库的订单
        int sum = ldOrderMapper.getCompleteOrder();
        model.addAttribute("sum",sum);
        return "laundryCompleteSum";
    }

    //当月天数
    public int getCurrentMonthLastDay()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public int getCurrentMonth()
    {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.MONTH) + 1;
        return maxDate;
    }

    @RequestMapping("/QRcode")
    public String QRcode(){
        return "clientManager/QRcode";
    }

    @RequestMapping("/addQr")
    public String addQr(){
        return "clientManager/addQr";
    }
    @RequestMapping("/addQrs")
    public String addQr(LdClient client){
        clientService.insertClient(client);
        return "clientManager/addQr";
    }
}