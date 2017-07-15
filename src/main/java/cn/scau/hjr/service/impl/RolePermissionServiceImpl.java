package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RolePermissionMapper;
import cn.scau.hjr.model.RolePermission;
import cn.scau.hjr.service.RolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
