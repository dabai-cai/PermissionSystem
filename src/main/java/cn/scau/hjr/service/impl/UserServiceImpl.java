package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.UserMapper;
import cn.scau.hjr.model.User;
import cn.scau.hjr.service.UserService;
import cn.scau.hjr.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource(name="userDao")
    private UserMapper userDao;

    public void addUser(User record) {

        userDao.insert(record);
        System.out.println("添加成功了!");
    }

    public void loginChk(User user) {

    }
}
