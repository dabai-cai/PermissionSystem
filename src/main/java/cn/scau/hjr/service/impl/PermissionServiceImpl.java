package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.PermissionMapper;
import cn.scau.hjr.model.Permission;
import cn.scau.hjr.service.PermissionService;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public boolean addPermission(Permission permission) {
        Permission temp=permissionMapper.selectByPermissionName(permission.getPermission());
        if(temp==null)
        {
            permissionMapper.insert(permission);
            return true;
        }
        return false;
    }

    @Override
    public void delPermissionById(int permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public void delByPermissionName(String permissionName) {
        permissionMapper.delByPermissionName(permissionName);
    }

    @Override
    public ArrayList<Permission> getAllPermission() {
        ArrayList<Permission> permissions=permissionMapper.getAllPermission();
        return permissions;
    }
}
