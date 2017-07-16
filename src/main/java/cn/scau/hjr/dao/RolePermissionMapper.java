package cn.scau.hjr.dao;

import cn.scau.hjr.model.RolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);
    ArrayList<RolePermission> selectByRoleId(@Param("roleId")Integer id);
    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
    RolePermission selectByRoleIdAndPermission(RolePermission rolePermission);
    void delRolePermissionByRoleId(@Param("roleId")int roleId);
    void delByRoleIdAndPermissionId(RolePermission rolePermission);
    void delByPermissionId(@Param("permissionId")int permissionId);
}