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
    User selectByAccountOrUsername(User user);
    int getAllUserNumber();
    ArrayList<User> getAllUser();
    ArrayList<User> getUserListByLimitNumber(@Param("start") int start, @Param("pagesize") int pagesize);
}