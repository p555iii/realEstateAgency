package com.real.cyd.resp;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 14:46
 **/
public class RespBeanOneObj {
    private String errorNo;
    private ResultOneObj results;

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public ResultOneObj getResults() {
        return results;
    }

    public void setResults(ResultOneObj results) {
        this.results = results;
    }
}