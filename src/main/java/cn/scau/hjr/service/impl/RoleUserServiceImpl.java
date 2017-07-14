package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleUserMapper;
import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import cn.scau.hjr.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
@Service()
public class RoleUserServiceImpl implements RoleUserService {

    @Resource(name="roleUserDao")
    public RoleUserMapper roleUserMapper;
    public RoleUser selectByUserIdAndRoleId(RoleUser roleUser) {
        roleUser=roleUserMapper.selectByUserIdAndRoleId(roleUser);
        return roleUser;
    }
    @Override
    public void delByUserId(int userId) {
        roleUserMapper.delByUserId(userId);
    }

    @Override
    public void insertRoleUser(RoleUser roleUser) {
        roleUserMapper.insert(roleUser);
    }

    @Override
    public ArrayList<RoleUser> selectByUserId(int userId) {
        ArrayList<RoleUser> roles=roleUserMapper.selectByUserId(userId);
        return roles;
    }

    @Override
    public int insert(RoleUser record) {
        roleUserMapper.insert(record);
        return 1;
    }
}
