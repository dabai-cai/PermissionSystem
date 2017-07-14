package cn.scau.hjr.dao;

import cn.scau.hjr.model.Role;
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
    ArrayList<Role> getAllRole();
    int updateByPrimaryKeySelective(Role record);



    int updateByPrimaryKey(Role record);
}