package cn.scau.hjr.service;

import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.User;
import cn.scau.hjr.model.User;

import java.util.ArrayList;

/**
 * Created by Zhangxq on 2016/7/15.
 */
public interface UserService {

   boolean addUser(User user);
   boolean loginChk(User user);
   User getUser(User user);
   User selectByPrimaryKey(Integer userId);
   User selectByAccountOrUsername(User user);
   Pager getUserPager();
   void delUserById(int id);
   ArrayList<User> getSearchUser(String key);

}
