package com.real.cyd.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="cyd")
public class PropBean {
    private String gg;
    private String kk;

    public String getGg() {
        return gg;
    }

    public String getKk() {
        return kk;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }
}
