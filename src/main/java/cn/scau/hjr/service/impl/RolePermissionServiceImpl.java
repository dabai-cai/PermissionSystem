package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RolePermissionMapper;
import cn.scau.hjr.model.RolePermission;
import cn.scau.hjr.service.RolePermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public boolean addRolePermission(RolePermission rolePermission) {
        RolePermission temp=rolePermissionMapper.selectByRoleIdAndPermission(rolePermission);
        if(temp==null)
        {
            rolePermissionMapper.insert(rolePermission);
            return true;
        }
        return false;
    }

    @Override
    public void delRolePermissionByRoleId(int roleId) {
          rolePermissionMapper.delRolePermissionByRoleId(roleId);
    }

    @Override
    public RolePermission selectByRoleIdAndPermission(RolePermission rolePermission) {
        RolePermission temp=rolePermissionMapper.selectByRoleIdAndPermission(rolePermission);
        return temp;
    }

    @Override
    public RolePermission selectByPrimaryKey(Integer id) {

        RolePermission rolePermission=null;
        rolePermission=rolePermissionMapper.selectByPrimaryKey(id);
        return rolePermission;

    }

    @Override
    public ArrayList<RolePermission> selectByRoleId(Integer id){
        ArrayList<RolePermission> rolePermissions=null;
        rolePermissions=rolePermissionMapper.selectByRoleId(id);
        return rolePermissions;
    }

    @Override
    public int insert(RolePermission record) {
        RolePermission rolePermission=rolePermissionMapper.selectByRoleIdAndPermission(record);
        if(rolePermission==null)
        {
            rolePermissionMapper.insert(record);
        }

        return 0;
    }

    @Override
    public void delByRoleIdAndPermissionId(RolePermission rolePermission) {
        rolePermissionMapper.delByRoleIdAndPermissionId(rolePermission);
    }

    @Override
    public void delByPermissionId(int permissionId) {
        rolePermissionMapper.delByPermissionId(permissionId);
    }
}
