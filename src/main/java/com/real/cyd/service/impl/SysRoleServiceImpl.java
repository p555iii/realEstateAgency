package com.real.cyd.service.impl;

import com.real.cyd.bean.*;
import com.real.cyd.mapper.SysRoleMapper;
import com.real.cyd.req.AuthPermissionReq;
import com.real.cyd.req.QueryRoleReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.SysRoleService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 17:37
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

    @Override
    public List<SysRole> getRoleList(SysUser userInfo) {

        return  roleMapper.getRoleList(userInfo);
    }

    @Override
    public RespBean queryRoleList(QueryRoleReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }

        List<SysRole> roleList = roleMapper.roleList(req);
        int count = roleMapper.getCount(req);
        if(roleList == null || roleList.size() == 0){
            return null;
        }

        for(SysRole role:roleList){
            if(role.getCreateTime() != null){
                role.setDateStr(ToolsUtils.getDateStr(role.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(roleList,count);
    }

    @Override
    public void insertRole(SysRole role) {
        if(role == null ){
            return;
        }
        if(role.getId() == null || role.getId().equals("")){
            role.setId(UUID.randomUUID().toString());
        }
        if(role.getCreateTime() == null){
            role.setCreateTime(new Date());
        }
        roleMapper.insertSelective(role);
    }

    @Override
    public RespBean deleteUser(String id) {
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = roleMapper.deleteByPrimaryKey(s);
            if(i == 0){
                result++;
            }
        }
        if(result == 0){
            res.setErrorNo("0");
        }
        return res;
    }

    @Override
    public RespBeanOneObj getRoleInfo(String id) {
        SysRole role = roleMapper.selectByPrimaryKey(id);
        return ToolsUtils.getRespOneObj(role);
    }

    @Override
    public void updateUser(SysRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<TreeMenuAllowAccess> perDistribution(String id) {

        //首先查到所有的权限  制成一个TreeMenuAllowAccess对象
        List<TreeMenuAllowAccess> list = new ArrayList<>();
        //查一级权限
        List<SysPermission> permissionslist = roleMapper.selectPermissionParent();
        for(SysPermission permission : permissionslist){
            //放入一级权限
            TreeMenuAllowAccess bean = new TreeMenuAllowAccess();
            List<TreeMenuAllowAccess> clist = new ArrayList<>();
            bean.setSysMenu(permission);
            //查询该权限对该角色是否开放  返回 int 1 代表有 0代表无
            int count = getBoolAuth(id,permission);
            //如果有权限就赋值为1
            if(count == 1){
                bean.setAllowAccess(true);
            }
            //查二级权限
            List<SysPermission> children = roleMapper.selectPermissionChildren(permission);
            //二级权限也要进行查询是否有权限
            for(SysPermission s:children){
                TreeMenuAllowAccess cbean = new TreeMenuAllowAccess();
                cbean.setSysMenu(s);
                count = getBoolAuth(id,s);
                if(count == 1){
                    cbean.setAllowAccess(true);
                }
                clist.add(cbean);
            }
            bean.setChildren(clist);
            list.add(bean);
        }
        return list;
    }

    @Override
    public void authPer(AuthPermissionReq req) {
        //删掉过去属于该角色的权限
        roleMapper.deleteAuthByRoleId(req);
        //解析现在的权限
        if(req.getMid() == null || req.getMid().equals("")){
            return;
        }
        String[] split = req.getMid().split(",");
        //赋予权限
        for(String auth:split) {
            Map<String,String> map = new HashMap<>();
            map.put("roleId",req.getRoleId());
            map.put("permissionId",auth);
            map.put("id",UUID.randomUUID().toString());
            roleMapper.authPer(map);
        }
    }

    @Override
    public List<SysRole> getRole() {
        return roleMapper.getRole();
    }

    private int getBoolAuth(String id, SysPermission permission) {
        Map<String,String> map = new HashMap();
        map.put("roleId",id);
        map.put("permissionId",permission.getId());
        return roleMapper.selectThisPermissonAuth(map);
    }
}