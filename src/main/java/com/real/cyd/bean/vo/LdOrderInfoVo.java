package com.real.cyd.bean.vo;

import java.util.Date;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-20 17:12
 **/
public class LdOrderInfoVo {
    private String infoId;

    private String orderId;

    private String clothestypeId;

    private String laundeytypeId;

    private Date createTime;

    private String clothesTypeName;

    private String laundeyTypeName;

    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String dateStr;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClothesTypeName() {
        return clothesTypeName;
    }

    public void setClothesTypeName(String clothesTypeName) {
        this.clothesTypeName = clothesTypeName;
    }

    public String getLaundeyTypeName() {
        return laundeyTypeName;
    }

    public void setLaundeyTypeName(String laundeyTypeName) {
        this.laundeyTypeName = laundeyTypeName;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}