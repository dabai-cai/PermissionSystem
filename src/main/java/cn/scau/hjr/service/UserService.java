package cn.scau.hjr.service;

import cn.scau.hjr.model.User;
import cn.scau.hjr.model.User;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {

   void addUser(User user);
   boolean loginChk(User user);
   User getUser(User user);
   User selectByPrimaryKey(Integer userId);

}
