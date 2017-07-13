package cn.scau.hjr.dao;

import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import org.springframework.stereotype.Repository;

@Repository(value = "roleUserDao")
public interface RoleUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleUser record);

    int insertSelective(RoleUser record);

    RoleUser selectByPrimaryKey(Integer id);

    RoleUser selectByUserIdAndRoleId(int userId,int roleId);
    int updateByPrimaryKeySelective(RoleUser record);

    int updateByPrimaryKey(RoleUser record);

    RoleUser selectByUserIdAndRoleId(RoleUser roleUser);
}