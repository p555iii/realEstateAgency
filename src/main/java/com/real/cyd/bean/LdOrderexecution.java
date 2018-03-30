package com.real.cyd.bean;

import java.util.Date;

public class LdOrderexecution {
    private String id;

    private String clientId;

    private Integer sum;

    private Date estimatedTime;

    private Integer state;

    private Integer isTakeAway;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Date getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Date estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getIsTakeAway() {
        return isTakeAway;
    }

    public void setIsTakeAway(Integer isTakeAway) {
        this.isTakeAway = isTakeAway;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}