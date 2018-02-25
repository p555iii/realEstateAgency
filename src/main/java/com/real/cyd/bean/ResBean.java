package com.real.cyd.bean;

import java.util.List;

public class  ResBean<E> {
    private int total;
    private List<E> list;

    public int getTotal() {
        return total;
    }

    public List<E> getList() {
        return list;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setList(List<E> list) {
        this.list = list;
    }
}
