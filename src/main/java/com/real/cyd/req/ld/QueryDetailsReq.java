package com.real.cyd.req.ld;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 17:09
 **/
public class QueryDetailsReq {
    private String id;
    private String infoId;
    private String orderId;
    private String clothestypeId;
    private String laundeytypeId;
    private String time;
    private String startTime;
    private String endTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getClothestypeId() {
        return clothestypeId;
    }

    public void setClothestypeId(String clothestypeId) {
        this.clothestypeId = clothestypeId;
    }

    public String getLaundeytypeId() {
        return laundeytypeId;
    }

    public void setLaundeytypeId(String laundeytypeId) {
        this.laundeytypeId = laundeytypeId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}