package cn.scau.hjr.service;

import cn.scau.hjr.model.RoleUser;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public interface RoleUserService {
    RoleUser selectByUserIdAndRoleId(RoleUser roleUser);

}
