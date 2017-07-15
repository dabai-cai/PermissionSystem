package cn.scau.hjr.service;

import cn.scau.hjr.model.Permission;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public interface PermissionService {
    boolean addPermission(Permission permission);
    void delPermissionById(int permissionId);
    void delByPermissionName(String permissionName);
    ArrayList<Permission> getAllPermission();
    Permission selectByPrimaryKey(Integer permissionId);
}
