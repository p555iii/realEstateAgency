package com.real.cyd.bean;

import java.util.Date;

public class LdOrderInfo {
    private String id;

    private String orderId;

    private String clothestypeId;

    private String laundeytypeId;

    private Date createTime;

    private String dateStr;

    private Integer sum;

    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getClothestypeId() {
        return clothestypeId;
    }

    public void setClothestypeId(String clothestypeId) {
        this.clothestypeId = clothestypeId == null ? null : clothestypeId.trim();
    }

    public String getLaundeytypeId() {
        return laundeytypeId;
    }

    public void setLaundeytypeId(String laundeytypeId) {
        this.laundeytypeId = laundeytypeId == null ? null : laundeytypeId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}