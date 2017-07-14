package cn.scau.hjr.service;

import cn.scau.hjr.model.Role;

import java.util.ArrayList;

public interface RoleService {
    Role getRoleByRolename(String rolename);
    Role getRoleById(int roleId);
    boolean addRole(Role role);
    void delRoleById(int id);
    void delByRoleName(String rolename);
    ArrayList<Role> getAllRole();
}