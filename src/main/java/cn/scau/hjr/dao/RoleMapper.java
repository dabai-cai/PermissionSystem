package cn.scau.hjr.dao;

import cn.scau.hjr.model.Role;
import org.springframework.stereotype.Repository;

@Repository(value = "roleDao")
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleId);

    Role selectByRoleName(String roleName);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}