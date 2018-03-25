package com.real.cyd.req.ld;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 13:43
 **/
public class QueryOrderReq {
    private String id;
    private String name;
    private String phone;
    private String emTime;
    private String time;
    private String startTime;
    private String endTime;
    private String emStartTime;
    private String emEndTime;

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

    public String getEmStartTime() {
        return emStartTime;
    }

    public void setEmStartTime(String emStartTime) {
        this.emStartTime = emStartTime;
    }

    public String getEmEndTime() {
        return emEndTime;
    }

    public void setEmEndTime(String emEndTime) {
        this.emEndTime = emEndTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmTime() {
        return emTime;
    }

    public void setEmTime(String emTime) {
        this.emTime = emTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}