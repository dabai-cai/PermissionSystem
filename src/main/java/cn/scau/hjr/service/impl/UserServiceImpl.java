package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleUserMapper;
import cn.scau.hjr.dao.UserMapper;
import cn.scau.hjr.model.*;
import cn.scau.hjr.service.PermissionService;
import cn.scau.hjr.service.RolePermissionService;
import cn.scau.hjr.service.RoleService;
import cn.scau.hjr.service.UserService;
import cn.scau.hjr.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    
    @Resource(name="userDao")
    private UserMapper userDao;
    @Resource(name="roleUserDao")
    private RoleUserMapper roleUserMapper;


    public boolean addUser(User record) {

        User user=userDao.selectByAccountOrUsername(record);
        if(user==null)
        {
            userDao.insert(record);//添加用户

            RoleUser roleUser=new RoleUser();
            roleUser.setUserId(record.getUserId());
            roleUser.setRoleId(2);//普通用户id
            roleUserMapper.insert(roleUser);//用户关联角色
            System.out.println("添加成功");
            return true;

        }
        else{
            return false;
        }

    }

    public boolean loginChk(User user) {
        User temp=userDao.getUserByAccountAndPassword(user);

        if(temp!=null)
        {
            return true;
        }
        return false;
    }

    public User getUser(User user) {
        User temp=null;
        temp=userDao.getUserByAccountAndPassword(user);
        return temp;
    }

    public  User selectByPrimaryKey(Integer userId)
    {
        User user=userDao.selectByPrimaryKey(userId);
        return user;
    }

    public User selectByAccountOrUsername(User user) {
        User user1=userDao.selectByAccountOrUsername(user);
        return user1;
    }

    @Override
    public Pager getUserPager() {

        Pager pager = new Pager();//建立分页对象


            int currentPage = SystemData.getPageOffset();//当前页
            Integer _pagesize = SystemData.getPageSize();
            int pagesize = _pagesize.intValue();//页面大小
            int start=currentPage * pagesize - pagesize;

            /*
            得到总页数和总留言数
             */
            int totalPage = 0;
            int totalGuest = 0;
            totalGuest=userDao.getAllUserNumber();
            System.out.println("总数据数目:"+totalGuest);
            totalPage = (totalGuest - 1) / pagesize + 1;//总页数

        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);

            //只取当前页面的留言，并存到List中


            List userList = new ArrayList<User>();//用来存储留言数据的list
            userList=userDao.getUserListByLimitNumber(start,pagesize);
            pager.setpagerData(userList);

            /*
            配置pager
             */

        return pager;
    }

    @Override
    public void delUserById(int id) {
        roleUserMapper.delByUserId(id);
        userDao.deleteByPrimaryKey(id);
    }

    @Override
    public ArrayList<User> getSearchUser(String key) {
        ArrayList<User> userList=new ArrayList<User>();
        userList=userDao.searchUser(key);
        return userList;

    }

    @Override
    public int updateByPrimaryKey(User record) {
        userDao.updateByPrimaryKey(record);
        return 0;
    }


}
