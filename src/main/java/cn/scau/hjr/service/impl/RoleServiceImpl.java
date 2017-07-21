package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.RoleMapper;
import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.SystemData;
import cn.scau.hjr.model.User;
import cn.scau.hjr.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/13 0013.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource(name="roleDao")
    public RoleMapper roleDao;
    public Role getRoleByRolename(String rolename) {
        Role role=null;
        role=roleDao.selectByRoleName(rolename);
        return role;
    }

    public Role getRoleById(int roleId) {
        Role role=null;
        role=roleDao.selectByPrimaryKey(roleId);
        return role;
    }

    @Override
    public boolean addRole(Role role) {
        Role role1=roleDao.selectByRoleName(role.getRolename());
        if(role1==null)
        {
            roleDao.insert(role);
            return true;
        }
        return false;
    }

    @Override
    public void delRoleById(int id) {
        roleDao.deleteByPrimaryKey(id);
    }

    @Override
    public void delByRoleName(String rolename) {
        roleDao.delByRoleName(rolename);
    }

    @Override
    public ArrayList<Role> getAllRole() {
        ArrayList<Role> roles=roleDao.getAllRole();
        return roles;
    }

    @Override
    public Pager getRolePager() {
        Pager pager=new Pager();
        int currentPage = SystemData.getPageOffset();//当前页
        Integer _pagesize = SystemData.getPageSize();
        int pagesize = _pagesize.intValue();//页面大小
        int start=currentPage * pagesize - pagesize+1;

            /*
            得到总页数和总留言数
             */
        int totalPage = 0;
        int totalGuest = 0;
        totalGuest=roleDao.getAllRoleNumber();
        System.out.println("总数据数目:"+totalGuest);
        totalPage = (totalGuest - 1) / pagesize + 1;//总页数

        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);

        //只取当前页面的留言，并存到List中


        List<Role> roleList = new ArrayList<Role>();//用来存储留言数据的list
        roleList=roleDao.getRoleListByLimitNumber(start,pagesize);
        pager.setpagerData(roleList);

        return pager;
    }

    @Override
    public Role selectByPrimaryKey(Integer roleId) {
        Role role=null;
        role=roleDao.selectByPrimaryKey(roleId);
        return  role;
    }

    @Override
    public void updateRole(Role role) {
        roleDao.updateByPrimaryKey(role);
    }
}
