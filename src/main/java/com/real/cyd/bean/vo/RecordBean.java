package com.real.cyd.bean.vo;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-25 20:16
 **/
public class RecordBean {
    private String isRecord;
    private int value;

    public String getIsRecord() {
        return isRecord;
    }

    public void setIsRecord(String isRecord) {
        this.isRecord = isRecord;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public RecordBean(String isRecord, int value) {
        this.isRecord = isRecord;
        this.value = value;
    }

    public RecordBean() {
    }
}