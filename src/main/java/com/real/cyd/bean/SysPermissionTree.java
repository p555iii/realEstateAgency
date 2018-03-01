package com.real.cyd.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-27 11:20
 **/
public class SysPermissionTree {
    public SysPermission sysPermission;
    public List<SysPermission> children = new ArrayList<>();

    public SysPermission getSysPermission() {
        return sysPermission;
    }

    public void setSysPermission(SysPermission sysPermission) {
        this.sysPermission = sysPermission;
    }

    public List<SysPermission> getChildren() {
        return children;
    }

    public void setChildren(List<SysPermission> children) {
        this.children = children;
    }
}