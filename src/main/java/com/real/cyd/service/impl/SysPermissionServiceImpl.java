package com.real.cyd.service.impl;

import com.real.cyd.bean.*;
import com.real.cyd.mapper.SysPermissionMapper;
import com.real.cyd.mapper.SysRoleMapper;
import com.real.cyd.req.QueryPermissionReq;
import com.real.cyd.resp.RespBeanOneObj;
import com.real.cyd.service.SysPermissionService;
import com.real.cyd.utils.ToolsUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @program: realEstateAgency
 * @description: ${description}
 * @author: cyd
 * @create: 2018-02-26 17:45
 **/
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Resource
    private SysPermissionMapper permissionMapper;

    @Resource
    private SysRoleMapper roleMapper;
    @Override
    public List<SysPermission> getPermissions(SysRole role) {
        return permissionMapper.getPermissions(role);
    }

    @Override
    public List<SysPermissionTree> selectTreeMenuByUserId(String id) {
        List<SysPermissionTree> list = new ArrayList<>();
        SysUser user = new SysUser();
        user.setId(id);
        //得到该用户的角色列表
        List<SysRole> roleList = roleMapper.getRoleById(user);

        for(SysRole role : roleList){
            //遍历角色 查询一级权限
            List<SysPermission> pPermission = permissionMapper.getPermissionsByDeep1(role);
            //通过一级权限遍历二级权限
            for(SysPermission permission :pPermission){

                SysPermissionTree tree = new SysPermissionTree();
                tree.setSysPermission(permission);
                Map<String,String> map = new HashMap<>();
                map.put("id",role.getId());
                map.put("parentId",permission.getId());
                List<SysPermission> cPermission = permissionMapper.getPermissionsByDeep2(map);
                tree.setChildren(cPermission);
                list.add(tree);
            }

        }
        return list;
    }

    @Override
    public RespBean queryPermissionList(QueryPermissionReq req) {
        if(req.getTime()!= null && !req.getTime().equals("")){
            String[] timeSplit = ToolsUtils.getTimeSplit(req.getTime());
            req.setStartTime(timeSplit[0]);
            req.setEndTime(timeSplit[1]);
        }

        List<SysPermission> roleList = permissionMapper.perList(req);
        int count = permissionMapper.getCount(req);
        if(roleList == null || roleList.size() == 0){
            return null;
        }

        for(SysPermission role:roleList){
            if(role.getCreateTime() != null){
                role.setDateStr(ToolsUtils.getDateStr(role.getCreateTime()));
            }
        }
        return ToolsUtils.getRespBean(roleList,count);
    }

    @Override
    public void insertPermission(SysPermission permission) {
        if(permission == null ){
            return;
        }
        if(permission.getId() == null || permission.getId().equals("")){
            permission.setId(UUID.randomUUID().toString());
        }
        if(permission.getCreateTime() == null){
            permission.setCreateTime(new Date());
        }
        if(permission.getAvailable() == null){
            permission.setAvailable(false);
        }
        if(permission.getParentId() != null && permission.getParentId().equals("")){
            permission.setParentId(null);
        }
        //对sort进行处理
        if(permission.getParentId() == null){
            int sort = permissionMapper.getMaxSortParent();
            permission.setSort(sort+1);
        }else if(permission.getParentId() != null){
            int  sort = permissionMapper.getMaxSortChildern(permission);
            permission.setSort(sort+1);
        }
        permissionMapper.insertSelective(permission);
    }

    @Override
    public RespBean deletePermission(String id) {
        RespBean res = new RespBean();
        int result = 0;
        String[] split = id.split(",");
        for(String s:split){
            int i = permissionMapper.deleteByPrimaryKey(s);
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
    public RespBeanOneObj getPermissionInfo(String id) {
        SysPermission permission = permissionMapper.selectByPrimaryKey(id);
        return ToolsUtils.getRespOneObj(permission);
    }

    @Override
    public void updatePermission(SysPermission permission) {
        if(permission.getAvailable() == null){
            permission.setAvailable(false);
        }
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public List<SysPermission> queryParentPermission() {
        return permissionMapper.queryParentPermission();
    }
}