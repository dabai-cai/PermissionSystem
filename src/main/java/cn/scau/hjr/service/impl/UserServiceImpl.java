package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.*;
import cn.scau.hjr.model.*;
import cn.scau.hjr.service.PermissionService;
import cn.scau.hjr.service.RolePermissionService;
import cn.scau.hjr.service.RoleService;
import cn.scau.hjr.service.UserService;
import cn.scau.hjr.model.User;
import cn.scau.hjr.util.shiroUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.swing.*;
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

        User user=userDao.selectByAccount(record);
        if(user==null) {
            record.setPassword(shiroUtil.encode(record.getPassword(),record.getAccount()));//加密
            userDao.insert(record);//添加用户
            Subject subject=SecurityUtils.getSubject();
            if(subject.hasRole("teacher")) {//判断是否为教师
                RoleUser r =new RoleUser();
                r.setRoleId(17);
                r.setUserId(record.getUserId());
                roleUserMapper.insert(r);
            }
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
        User user1=userDao.selectByAccount(user);
    //    user1.setPassword(ShiroUtils.deCodeToString(user.getPassword()));
        return user1;
    }
    @Override
    public void delUserById(int id) {
        roleUserMapper.delByUserId(id);
        userDao.deleteByPrimaryKey(id);
    }


    @Override
    public int updateByPrimaryKey(User record) {
        User user=userDao.selectByPrimaryKey(record.getUserId());
        record.setPassword(shiroUtil.encode(record.getPassword(),user.getAccount()));//加密
        userDao.updateByPrimaryKey(record);
        return 0;
    }

    @Override
    public ArrayList<User> getAllUser() {

        return userDao.getAllUser();
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


    //通过角色给予相应的数据
    @Override
    public Pager getUserPageByRole() {
        Pager pager=new Pager();
        Subject currentSubject= SecurityUtils.getSubject();
        int currentPage = SystemData.getPageOffset();//当前页
        Integer _pagesize = SystemData.getPageSize();
        int pagesize = _pagesize.intValue();//页面大小
        int start=currentPage * pagesize - pagesize;
        int totalPage = 0;
        int totalGuest = 0;
        ArrayList<User> userList=new ArrayList<User>();
            if(currentSubject.hasRole("root")) {  //返回管理员
                totalGuest=userDao.getAllUserNumber();
                userList=userDao.getUserListByLimitNumber(start,pagesize);
            }
            else{
                Integer[] studentIds=roleUserMapper.getUserGroupByRoleId(17,start,pagesize);
                userList=userDao.getStudentGroup(studentIds);
            /*
            得到总页数和总留言数和用户数据
             */
                totalGuest=roleUserMapper.getUserRoleNumber(17);//得到学生总数


            }
        for(User user:(ArrayList<User>)userList) {
            user.setHasRole(this.getUserRoles(user.getAccount()));//用户拥有的角色
            user.setLacksRole(this.getUserLacksRoles(user.getAccount()));//用户缺乏的角色
        }
        totalPage = (totalGuest - 1) / pagesize + 1;//总页数
        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);
        pager.setpagerData(userList);
        return pager;
    }

    @Override
    public Pager getSearchUserPageByRole(String name, int roleId) {


        Pager pager = new Pager();//建立分页对象

        int currentPage = SystemData.getPageOffset();//当前页
        Integer _pagesize = SystemData.getPageSize();
        int pagesize = _pagesize.intValue();//页面大小
        int start=currentPage * pagesize - pagesize;
        /*
            得到总页数和总留言数和用户数据
         */
        int totalPage = 0;
        int totalGuest = 0;
        ArrayList<User> userList=new ArrayList<User>();
        Subject subject=SecurityUtils.getSubject();
        if(subject.hasRole("root")){
            userList=userDao.searchUser(name,start,pagesize);
            totalGuest=userDao.getSearchUserCount(name);
        }else{//教师搜索学生
            userList=userDao.searchUserByRole(name,roleId,start,pagesize);
            totalGuest=userDao.searchUserByRoleCount(name,roleId);
        }
        for(User user:userList) {
            user.setHasRole(this.getUserRoles(user.getAccount()));//用户拥有的角色
            user.setLacksRole(this.getUserLacksRoles(user.getAccount()));//用户缺乏的角色
        }

        totalPage = (totalGuest - 1) / pagesize + 1;//总页数
        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);
        pager.setpagerData(userList);
        //只取当前页面的留言，并存到List中
        return pager;
    }


}
