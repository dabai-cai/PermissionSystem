package cn.scau.hjr.service;

import cn.scau.hjr.model.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public interface RolePermissionService {
    boolean addRolePermission(RolePermission rolePermission);
    void delRolePermissionByRoleId(int roleId);
    RolePermission selectByRoleIdAndPermission(RolePermission rolePermission);
    RolePermission selectByPrimaryKey(Integer id);
    ArrayList<RolePermission> selectByRoleId(@Param("roleId")Integer id);
    int insert(RolePermission record);
    void delByRoleIdAndPermissionId(RolePermission rolePermission);
}
