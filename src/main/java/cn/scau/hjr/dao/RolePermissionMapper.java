package cn.scau.hjr.dao;

import cn.scau.hjr.model.RolePermission;

public interface RolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);
}