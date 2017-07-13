package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleMapper;
import cn.scau.hjr.model.Role;
import cn.scau.hjr.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource(name="roleDao")
    public RoleMapper roleDao;
    public Role getRoleByRolename(String rolename) {
        Role role=null;
        role=roleDao.selectByRoleName(rolename);
        return role;
    }

    public Role getRoleById(int roleId) {
        Role role=null;
        role=roleDao.selectByPrimaryKey(roleId);
        return role;
    }
}
