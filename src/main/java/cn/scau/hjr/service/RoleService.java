package cn.scau.hjr.service;

import cn.scau.hjr.model.Role;

public interface RoleService {
    Role getRoleByRolename(String rolename);
    Role getRoleById(int roleId);
}