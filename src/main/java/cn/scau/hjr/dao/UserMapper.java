package cn.scau.hjr.dao;

import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository(value = "userDao")
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User getUserByAccountAndPassword(User user);
    User selectByAccount(User user);
    int getAllUserNumber();
    ArrayList<User> getAllUser();
    ArrayList<User> getUserListByLimitNumber(@Param("start") int start, @Param("pagesize") int pagesize);
    ArrayList<User> searchUser(String key,int start,int pagesize);
    int getSearchUserCount(String key);
    User getUserByAccount(String account);
    ArrayList<User> getStudentGroup(@Param("array") Integer[] studentIds);
    ArrayList<User> searchUserByRole(String keyName,int roleId,int start,int pagesize);
    int searchUserByRoleCount(String keyName,int roleId);
    //@Param("start")int start,@Param("pagesize") int pagesize,
}