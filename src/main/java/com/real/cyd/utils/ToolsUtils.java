package com.real.cyd.utils;

import com.real.cyd.bean.ResBean;
import com.real.cyd.bean.RespBean;
import com.real.cyd.bean.Result;
import com.real.cyd.bean.SysUser;

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
}
