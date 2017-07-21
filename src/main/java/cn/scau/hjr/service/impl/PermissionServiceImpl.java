package cn.scau.hjr.service.impl;

import cn.scau.hjr.dao.PermissionMapper;
import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.Permission;
import cn.scau.hjr.model.SystemData;
import cn.scau.hjr.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Override
    public boolean addPermission(Permission permission) {
        Permission temp=permissionMapper.selectByPermissionName(permission.getPermission());
        if(temp==null)
        {
            permissionMapper.insert(permission);
            return true;
        }
        return false;
    }

    @Override
    public void delPermissionById(int permissionId) {
        permissionMapper.deleteByPrimaryKey(permissionId);
    }

    @Override
    public void delByPermissionName(String permissionName) {
        permissionMapper.delByPermissionName(permissionName);
    }

    @Override
    public Permission selectByPrimaryKey(Integer permissionId) {
        Permission permission=null;
        permission=permissionMapper.selectByPrimaryKey(permissionId);
        return permission;
    }

    @Override
    public Pager getPermissionPager() {
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
        totalGuest=permissionMapper.getPermissionNumber();
        totalPage = (totalGuest - 1) / pagesize + 1;//总页数
        pager.setPageSize(pagesize);
        pager.setPageOffset(SystemData.getPageOffset());
        pager.setTotalPage(totalPage);
        pager.setTotalGuest(totalGuest);
        pager.setStart(start);

        //只取当前页面的留言，并存到List中

        List<Permission> roleList = new ArrayList<Permission>();//用来存储留言数据的list
        roleList=permissionMapper.getAllPermissionLimit(start,pagesize);
        pager.setpagerData(roleList);
        return pager;
    }

    @Override
    public ArrayList<Permission> getAllPermission() {
        ArrayList<Permission> permissions=permissionMapper.getAllPermission();
        return permissions;
    }
}
