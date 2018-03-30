package com.real.cyd.req.fin;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-26 12:24
 **/
public class FinRecordReq {
    private String id;
    private Integer isrecord;
    private String phone;
    private String userName;
    private String time;
    private String startTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsrecord() {
        return isrecord;
    }

    public void setIsrecord(Integer isrecord) {
        this.isrecord = isrecord;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}