package cn.scau.hjr.dao;

import cn.scau.hjr.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Integer permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Integer permissionId);
    Permission selectByPermissionName(@Param("permission")String permission);
    void delByPermissionName(@Param("permission")String permission);
    ArrayList<Permission> getAllPermissionLimit(int start,int pageindex);
    ArrayList<Permission> getAllPermission();
    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
    int getPermissionNumber();
}