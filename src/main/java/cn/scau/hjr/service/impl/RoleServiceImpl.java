package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleMapper;
import cn.scau.hjr.model.*;
import cn.scau.hjr.service.PermissionService;
import cn.scau.hjr.service.RolePermissionService;
import cn.scau.hjr.service.RoleService;
import org.omg.CORBA.SetOverrideTypeHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource(name = "roleDao")
    public RoleMapper roleDao;
    @Resource
    public PermissionService permissionService;
    @Resource
    public RolePermissionService rolePermissionService;


    public Role getRoleByRolename(String rolename) {
        Role role = null;
        role = roleDao.selectByRoleName(rolename);
        return role;
    }

    public Role getRoleById(int roleId) {
        Role role = null;
        role = roleDao.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public boolean addRole(Role role) {
        Role role1 = roleDao.selectByRoleName(role.getRolename());
        if (role1 == null) {
            roleDao.insert(role);
            return true;
        }
        return false;
    }

    @Override
    public void delRoleById(int id) {
        roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public void delByRoleName(String rolename) {
        roleDao.delByRoleName(rolename);
    }

    @Override
    public ArrayList<Role> getAllRole() {
        ArrayList<Role> roles = roleDao.getAllRole();
        return roles;
    }

    @Override
    public Pager getRolePager() {
        Pager pager = new Pager();
        int currentPage = SystemData.getPageOffset();//当前页
        Integer _pagesize = SystemData.getPageSize();
        int pagesize = _pagesize.intValue();//页面大小
        int start = currentPage * pagesize - pagesize + 1;

            /*
            得到总页数和总留言数
             */
        int totalPage = 0;
        int totalGuest = 0;
        totalGuest = roleDao.getAllRoleNumber();
        System.out.println("总数据数目:" + totalGuest);
        totalPage = (totalGuest - 1) / pagesize + 1;//总页数

        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);

        //只取当前页面的留言，并存到List中


        List<Role> roleList = new ArrayList<Role>();//用来存储留言数据的list
        roleList = roleDao.getRoleListByLimitNumber(start, pagesize);
        for (Role role : roleList) {
            role.setHasPermission(this.getRolePermissions(role.getRoleId()));
            role.setLacksPermission(this.lacksPermissions(role.getRoleId()));
        }
        pager.setpagerData(roleList);

        return pager;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        Role role = null;
        role = roleDao.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateByPrimaryKey(role);
    }


    public Set<String> getRolePermissions(int roleId) {
        Set<String> hasPermissions = new HashSet<String>();
        ArrayList<RolePermission> rolePermissions = rolePermissionService.selectByRoleId(roleId);
        for (RolePermission rolePermission : rolePermissions) {
            Permission permission = permissionService.selectByPrimaryKey(rolePermission.getPermissionId());
            hasPermissions.add(permission.getPermission());
        }
        return hasPermissions;
    }

    public Set<String> lacksPermissions(int roleId) {
        Set<String> lacksPermissions = new HashSet<String>();
        Set<String> hasPermissions = this.getRolePermissions(roleId);
        Set<String> allPermissions = new HashSet<String>();
        ArrayList<Permission> permissions = permissionService.getAllPermission();
        for (Permission permission : permissions) {
            allPermissions.add(permission.getPermission());
        }
        for (String permissionName : allPermissions) {
            if (!hasPermissions.contains(permissionName)) {
                lacksPermissions.add(permissionName);
            }
        }
        return lacksPermissions;

    }
}