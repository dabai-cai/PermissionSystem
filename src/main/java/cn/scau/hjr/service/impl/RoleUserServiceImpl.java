package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleUserMapper;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Resource(name="roleUserDao")
    public RoleUserMapper roleUserMapper;
    public RoleUser selectByUserIdAndRoleId(RoleUser roleUser) {
        roleUser=roleUserMapper.selectByUserIdAndRoleId(roleUser);
        return roleUser;
    }
}
