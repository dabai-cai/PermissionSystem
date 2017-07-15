package cn.scau.hjr.dao;

import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository(value = "roleDao")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    Role selectByRoleName(@Param("rolename")String roleName);
    void delByRoleName(@Param("rolename")String rolename);
    int updateByPrimaryKeySelective(Role record);
    ArrayList<Role> getAllRole();
    ArrayList<Role> getRoleListByLimitNumber(@Param("start")int start, @Param("pagesize")int pagesize);
    int getAllRoleNumber();


    int updateByPrimaryKey(Role record);
}