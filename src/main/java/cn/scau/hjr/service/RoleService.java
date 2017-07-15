package cn.scau.hjr.service;

import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.User;

import java.util.ArrayList;

public interface RoleService {
    Role getRoleByRolename(String rolename);
    Role getRoleById(int roleId);
    boolean addRole(Role role);
    void delRoleById(int id);
    void delByRoleName(String rolename);
    ArrayList<Role> getAllRole();
    Pager getRolePager();
    Role selectByPrimaryKey(Integer roleId);

}