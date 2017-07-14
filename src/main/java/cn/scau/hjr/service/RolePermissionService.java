package cn.scau.hjr.service;

import cn.scau.hjr.model.RolePermission;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
public interface RolePermissionService {
    boolean addRolePermission(RolePermission rolePermission);
    void delRolePermissionByRoleId(int roleId);
    RolePermission selectByRoleIdAndPermission(RolePermission rolePermission);
}
