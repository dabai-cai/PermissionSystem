package cn.scau.hjr.service;

import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.Permission;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public interface PermissionService {
    boolean addPermission(Permission permission);
    void delPermissionById(int permissionId);
    void delByPermissionName(String permissionName);
    Permission selectByPrimaryKey(Integer permissionId);
    Pager getPermissionPager();
    ArrayList<Permission> getAllPermission();
    int updateByPrimaryKey(Permission record);
}
