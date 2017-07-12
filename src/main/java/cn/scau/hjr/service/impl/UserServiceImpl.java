package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.TUserMapper;
import cn.scau.hjr.dao.UserDao;
import cn.scau.hjr.model.TUser;
import cn.scau.hjr.service.UserService;
import cn.scau.hjr.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource(name="userDao")
    private TUserMapper userDao;

    public void addUser(TUser record) {
        TUser user=new TUser();
        user.setUserName("huangirngon");
           userDao.insert(user);
        System.out.println("添加成功!");
    }
}
