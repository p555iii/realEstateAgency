package com.real.cyd.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-28 13:03
 **/
public class TreeMenuAllowAccess implements Serializable {

    /**
     * @Fields serialVersionUID : TODO()
     */

    private static final long serialVersionUID = 1L;

    /**
     * 菜单
     */
    private SysPermission sysMenu;
    /**
     * 是否允许访问
     */
    private boolean allowAccess = false;
    /**
     * 子菜单
     */
    private List<TreeMenuAllowAccess> children = new ArrayList<TreeMenuAllowAccess>();

    public SysPermission getSysMenu() {
        return sysMenu;
    }
    public void setSysMenu(SysPermission sysMenu) {
        this.sysMenu = sysMenu;
    }
    public boolean isAllowAccess() {
        return allowAccess;
    }
    public void setAllowAccess(boolean allowAccess) {
        this.allowAccess = allowAccess;
    }
    public List<TreeMenuAllowAccess> getChildren() {
        return children;
    }
    public void setChildren(List<TreeMenuAllowAccess> children) {
        this.children = children;
    }

}
