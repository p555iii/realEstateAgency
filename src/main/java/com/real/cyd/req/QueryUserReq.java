package com.real.cyd.req;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 12:36
 **/
public class QueryUserReq {
    private String id;
    private String username;
    private String time;
    private String startTime;
    private String endTime;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTime() {
        return time;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}