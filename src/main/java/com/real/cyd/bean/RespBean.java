package com.real.cyd.bean;

public class RespBean {
    private String errorNo;
    private String errorInfo;
    private Result results;

    public String getErrorNo() {
        return errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public Result getResults() {
        return results;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setResults(Result results) {
        this.results = results;
    }
}
