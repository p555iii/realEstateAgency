package com.real.cyd.resp;

import java.math.BigDecimal;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-27 15:06
 **/
public class ResMainBean {
    private int toDayOrder; //今日订单
    private int countOrder; //累计订单
    private int toDayLaundry; //今日入库洗衣数
    private double toDayRecord;//今日入账
    private double toDayDebit;//今日出账
    private double countRecord;//累计账单

    public int getToDayOrder() {
        return toDayOrder;
    }

    public void setToDayOrder(int toDayOrder) {
        this.toDayOrder = toDayOrder;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }

    public int getToDayLaundry() {
        return toDayLaundry;
    }

    public void setToDayLaundry(int toDayLaundry) {
        this.toDayLaundry = toDayLaundry;
    }

    public double getToDayRecord() {
        return toDayRecord;
    }

    public void setToDayRecord(double toDayRecord) {
        this.toDayRecord = toDayRecord;
    }

    public double getToDayDebit() {
        return toDayDebit;
    }

    public void setToDayDebit(double toDayDebit) {
        this.toDayDebit = toDayDebit;
    }

    public double getCountRecord() {
        return countRecord;
    }

    public void setCountRecord(double countRecord) {
        this.countRecord = countRecord;
    }
}