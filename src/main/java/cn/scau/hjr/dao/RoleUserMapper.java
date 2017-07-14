package cn.scau.hjr.dao;

import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
    void delByUserId(@Param("userId")int userId);
    ArrayList<RoleUser> selectByUserId(@Param("userId")int id);
}