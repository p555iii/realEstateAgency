package com.real.cyd.resp;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-03-30 10:53
 **/
public class EChartResp {
    private String row; // 横
    private String line; //竖
    private int month; //月份

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}