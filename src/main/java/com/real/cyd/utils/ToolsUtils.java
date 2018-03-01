package com.real.cyd.utils;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.Result;
import com.real.cyd.bean.SysUser;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.resp.ResultOneObj;
import org.jetbrains.annotations.Contract;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author cyd
 * @time 2018-2-25 18:33
 */
public class ToolsUtils {
    /**
     * 适合本项目的返回分页list的工具方法
     * @param list
     * @param count
     * @param <E>
     * @return
     */
    public static <E> RespBean getRespBean(List<E> list, int count){

        ResBean<E> res = new ResBean<>();
        res.setList(list);
        res.setTotal(count);
        RespBean respBean = new RespBean();
        respBean.setErrorNo("0");
        Result result = new Result();
        result.setData(res);
        respBean.setResults(result);
        return respBean;
    }

    public static <E>RespBeanOneObj getRespOneObj(E obj){
        RespBeanOneObj res = new RespBeanOneObj();
        res.setErrorNo("0");
        ResultOneObj result = new ResultOneObj();
        result.setData(obj);
        res.setResults(result);
        return  res;
    }

    /**
     * 日期格式化
     * @param date
     * @return
     */
    @org.jetbrains.annotations.NotNull
    public static String getDateStr(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 日期格式化
     * @param dateStr
     * @return
     */
    public static Date getStrDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parse = null;
        try {
            parse = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 处理本项目中的createTime 和 endTime
     * 由于项目选择时间使用的layer中的，所以会出现这种 时间段是 2018-02-15 - 2018-03-21 这样的情况所以我们需要对他进行一下处理
     */
    public static String[] getTimeSplit(String time){

        if(time != null && !time.equals("")){
            String[] split = time.split("-");
            for(String s :split){
                System.out.println(s+"    ");
            }
            String [] res = new String[2];
            res[0] = split[0]+"-"+split[1]+"-"+split[2];
            res[1] = split[3]+"-"+split[4]+"-"+split[5];
            return res;
        }
        return null;
    }
}
