package com.real.cyd.bean;

import java.util.Date;

public class FinSource {
    private String id;

    private String name;

    private Integer isrecord;

    private Date createtime;

    private String dateStr;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsrecord() {
        return isrecord;
    }

    public void setIsrecord(Integer isrecord) {
        this.isrecord = isrecord;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}