package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.*;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;


    public boolean addUser(User record) {

        User user=userDao.selectByAccountOrUsername(record);
        if(user==null)
        {
            //record.setPassword(ShiroUtils.encodeToString(user.getPassword()));//加密
            userDao.insert(record);//添加用户

            RoleUser roleUser=new RoleUser();
            roleUser.setUserId(record.getUserId());
            roleUser.setRoleId(2);//普通用户id
            roleUserMapper.insert(roleUser);//用户关联角色
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
    //    temp.setPassword(ShiroUtils.deCodeToString(temp.getPassword()));
        return temp;
    }

    public  User selectByPrimaryKey(Integer userId)
    {
        User user=userDao.selectByPrimaryKey(userId);
     //   user.setPassword(ShiroUtils.deCodeToString(user.getPassword()));
        return user;
    }

    public User selectByAccountOrUsername(User user) {
        User user1=userDao.selectByAccountOrUsername(user);
    //    user1.setPassword(ShiroUtils.deCodeToString(user.getPassword()));
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
            totalPage = (totalGuest - 1) / pagesize + 1;//总页数

        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);

            //只取当前页面的留言，并存到List中

            List userList = new ArrayList<User>();//用来存储留言数据的list
            userList=userDao.getUserListByLimitNumber(start,pagesize);
            for(User user:(ArrayList<User>)userList)
            {
                user.setHasRole(this.getUserRoles(user.getAccount()));//用户拥有的角色
                user.setLacksRole(this.getUserLacksRoles(user.getAccount()));//用户缺乏的角色
            }
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
    public int updateByPrimaryKey(User record) {
        userDao.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public ArrayList<User> getAllUser() {

        return userDao.getAllUser();
    }

    @Override
    public Pager getSearchPager(String name) {
        Pager pager = new Pager();//建立分页对象

        int currentPage = SystemData.getPageOffset();//当前页
        Integer _pagesize = SystemData.getPageSize();
        int pagesize = _pagesize.intValue();//页面大小
        int start=currentPage * pagesize - pagesize;
        ArrayList<User> userList=new ArrayList<User>();
        userList=userDao.searchUser(name,start,pagesize);
            /*
            得到总页数和总留言数和用户数据
             */
        int totalPage = 0;
        int totalGuest = 0;
        totalGuest=userDao.getSearchUserCount(name);
        totalPage = (totalGuest - 1) / pagesize + 1;//总页数
            pager.setPageSize(pagesize);
            pager.setPageOffset(SystemData.getPageOffset());
            pager.setTotalPage(totalPage);
            pager.setTotalGuest(totalGuest);
            pager.setStart(start);
        for(User user:userList)
        {
            user.setHasRole(this.getUserRoles(user.getAccount()));//用户拥有的角色
            user.setLacksRole(this.getUserLacksRoles(user.getAccount()));//用户缺乏的角色
        }
            pager.setpagerData(userList);

            //只取当前页面的留言，并存到List中
        return pager;
    }

    @Override
    public Set<String> getUserRoles(String account) {
        User user=userDao.getUserByAccount(account);
        ArrayList<RoleUser> roleUsers=roleUserMapper.selectByUserId(user.getUserId());
        Set<String> roles=new HashSet<String>();
        for(RoleUser roleUser:roleUsers)
        {
            Role role=roleMapper.selectByPrimaryKey(roleUser.getRoleId());
            roles.add(role.getRolename());
        }
        return roles;
    }



    @Override
    public Set<String> getUserPermissions(String account) {
        Set<String> permissions=new HashSet<String>();
        User user=userDao.getUserByAccount(account);
        ArrayList<RoleUser> roleUsers=roleUserMapper.selectByUserId(user.getUserId());
        for(RoleUser roleUser:roleUsers)
        {
            Role role=roleMapper.selectByPrimaryKey(roleUser.getRoleId());
            List<RolePermission> RolePermissions=rolePermissionMapper.selectByRoleId(role.getRoleId());
            for(RolePermission rolePermission:RolePermissions)
            {
                Permission permission=permissionMapper.selectByPrimaryKey(rolePermission.getPermissionId());
                permissions.add(permission.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public User getUserByAccount(String account) {
        User user=new User();
        user=userDao.getUserByAccount(account);
        return user;
    }

    @Override
    public Set<String> getUserLacksRoles(String account) {
        Set<String> lacksRoles=new HashSet<String>();
        Set<String> hasRoles=this.getUserRoles(account);
        ArrayList<Role> roles=this.roleMapper.getAllRole();
        for(Role role:roles)
        {
            String rolename=role.getRolename();
            if(!hasRoles.contains(rolename))
            {
                lacksRoles.add(rolename);
            }
        }
        return lacksRoles;
    }


}
