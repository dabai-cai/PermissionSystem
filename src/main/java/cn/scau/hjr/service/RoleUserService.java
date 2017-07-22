package cn.scau.hjr.service;

import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
public interface RoleUserService {
    RoleUser selectByUserIdAndRoleId(RoleUser roleUser);
    void delByUserId(int userId);
    ArrayList<RoleUser> selectByUserId(int userId);
    int insert(RoleUser record);
    void delByRoleId(int roleId);
    void delByRoleIdAndUserId(int roleId, int userId);

}
