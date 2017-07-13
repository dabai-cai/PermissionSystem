package cn.scau.hjr.dao;

import cn.scau.hjr.model.User;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    void addUser(User user);
}