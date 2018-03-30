package com.real.cyd.bean.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-29 13:41
 **/
public class OrderExecutionVo {
    private String id;

    private String clientId;

    private Integer sum;

    private Date estimatedTime;

    private BigDecimal price;

    private BigDecimal realPrice;

    private Date createTime;

    private String name;

    private String phone;

    private String dateStr;

    private String emDateStr;  // 预计取回时间

    private Integer state;

    private Integer isTakeAway;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getEmDateStr() {
        return emDateStr;
    }

    public void setEmDateStr(String emDateStr) {
        this.emDateStr = emDateStr;
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
}