package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/7/14 0014.
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {
    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private RoleUserService roleUserService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private PermissionService permissionService;


    /*
    用户管理
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(HttpServletRequest request)
    {
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String username=request.getParameter("username");
       // password= ShiroUtils.encodeToString(password);
        String sex=request.getParameter("sex");

        System.out.println(request.getParameter("age"));
         String  phone=request.getParameter("phone");
        System.out.println(phone);
        int age=Integer.parseInt(request.getParameter("age"));
        System.out.println(age);
        User user=new User();
        user.setAccount(account);

        user.setPassword(password);

        user.setSex(sex);

        user.setPhone(phone);

        user.setAge(age);


        user.setUsername(username);

        if(userService.addUser(user))
        {   Pager pager=null;
            pager=userService.getUserPager();
            HttpSession session=request.getSession();
            session.setAttribute("pager",pager);
            return "/admin/UserManager";
        }

        else{
            return "/admin/addUserError";
        }
    }

    @RequestMapping(value = "/userManager",method = RequestMethod.GET)
    public String userManager(HttpServletRequest request,HttpSession session,HttpServletResponse response)
    {
        Subject subject= SecurityUtils.getSubject();
        Pager pager=null;
        if(subject.hasRole("root"))
        {
            int ifindex=0;
            String key=request.getParameter("searchUser");
            if(key!=null)session.setAttribute("key",key);

            try{
                ifindex=Integer.parseInt(request.getParameter("ifindex"));
            }catch (Exception e)
            {
                ifindex=0;
            }
            if(ifindex==1)
            {
                session.setAttribute("searchbool",new Boolean(false));
                session.setAttribute("key",null);
                pager=userService.getUserPager();
            }
            else{
                String searchName=(String)session.getAttribute("key");
                System.out.println("查询名字:"+key);
                if(searchName==null)
                {
                    pager=userService.getUserPager();
                }
                else{
                    pager=userService.getSearchPager(searchName);
                }
            }

            session.setAttribute("pager",pager);
            return "/admin/UserManager";
        }
        else{
            try{
                response.sendRedirect("/user/index");
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }
      return "/user/error";
    }

    @RequestMapping(value = "/userManager",method = RequestMethod.POST)
    public String userManager()
    {
        return "/admin/UserManager";
    }


    @RequestMapping(value = "/delUser")
    public void delUser(HttpServletRequest request, HttpServletResponse response)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        int pageindex=1;
        try{
            pageindex=Integer.parseInt(request.getParameter("pageindex"));
        }catch (Exception e)
        {
            pageindex=1;
        }
        userService.delUserById(id);
        roleUserService.delByUserId(id);
        System.out.println("删除成功"+id);
        try{
            System.out.println("当前页"+pageindex);
            response.sendRedirect("/admin/userManager?pageindex="+pageindex);
        }catch (Exception ex)
        {

        }

    }

    @RequestMapping(value = "/updateUser")
    public void   userUpdate(HttpServletRequest request,HttpServletResponse response)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        int pageindex=1;
        try{
            pageindex=Integer.parseInt(request.getParameter("pageindex"));
        }catch (Exception e)
        {
            pageindex=1;
        }
        User user0=new User();
        user0.setUserId(id);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int age=Integer.parseInt(request.getParameter("age"));
        String phone=request.getParameter("phone");
        String sex=request.getParameter("sex");
        user0.setSex(sex);
        user0.setUsername(username);
        user0.setAge(age);
        user0.setPhone(phone);
        user0.setPassword(password);
        userService.updateByPrimaryKey(user0);
        System.out.println("修改");
        /*
        Pager pager=null;
        pager=userService.getUserPager();
        HttpSession session=request.getSession();
        session.setAttribute("pager",pager);
        System.out.println("pager:"+pager);
        */
        try{
            response.sendRedirect(request.getContextPath()+"/admin/userManager?pageindex="+pageindex);
            System.out.println("可以！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        }catch (Exception e)
        {
            System.out.println("不行阿斯蒂芬是发送到 发");
        }

       // return "/admin/UserManager";
    }

    @RequestMapping(value = "/index")
    public String toIndex()
    {
        return "/BackGround/index";
    }



    @RequestMapping(value = "/AssigningRoles")
    public String AssigningRoles(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        User user=userService.selectByPrimaryKey(id);
        List<Role> roles=this.getRolesNotForUser(id);
        Set<Role> roleForUser=this.getRolesForUser(id);
        request.setAttribute("roleForUser",roleForUser);
        request.setAttribute("user",user);
        request.setAttribute("roles",roles);
        return "/admin/AssigningRoles";
    }
    @RequestMapping(value = "permissionAassign")
    public String permissionAassign(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        Role role=roleService.selectByPrimaryKey(id);
        List< Permission> permissions=this.getPermissionNotForRole(id);
        Set<Permission> permissionsForRole=this.getPermissionsForRole(id);
        request.setAttribute("permissionsForRole",permissionsForRole);
        request.setAttribute("role",role);
        request.setAttribute("permissions",permissions);
        return "/admin/permissionAassign";
    }

    //给用户分配角色
    @RequestMapping(value = "/roleForUser")
    public String roleForUser(HttpServletRequest request)
    {
        int userId=Integer.parseInt(request.getParameter("userId"));
        int roleId=Integer.parseInt(request.getParameter("roleId"));
        RoleUser roleUser=new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        roleUserService.insert(roleUser);
        return "/admin/UserManager";
    }
    //删除用户角色
    @RequestMapping(value = "/roledelUser")
    public String roleDelUser(HttpServletRequest request)
    {
        int userId=Integer.parseInt(request.getParameter("userId"));
        int roleId=Integer.parseInt(request.getParameter("roleId"));
        RoleUser roleUser=new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        roleUserService.delByRoleIdAndUserId(roleId,userId);
        return "/admin/UserManager";
    }

    //给角色分配权利
    @RequestMapping(value = "/permissionForRole")
    public String permissionForRole(HttpServletRequest request)
    {
        int roleId=Integer.parseInt(request.getParameter("roleId"));
        int permissionId=Integer.parseInt(request.getParameter("permissionId"));
        RolePermission rolePermission=new RolePermission();
        rolePermission.setPermissionId(permissionId);
        rolePermission.setRoleId(roleId);
        rolePermissionService.insert(rolePermission);
        return "/admin/RoleManager";
    }

    //删除角色的权利
    @RequestMapping(value = "/delPermissionRole")
    public String delPermissionRole(HttpServletRequest request)
    {
        int roleId=Integer.parseInt(request.getParameter("roleId"));
        int permissionId=Integer.parseInt(request.getParameter("permissionId"));
        RolePermission rolePermission=new RolePermission();
        rolePermission.setPermissionId(permissionId);
        rolePermission.setRoleId(roleId);
        rolePermissionService.delByRoleIdAndPermissionId(rolePermission);

        return "/admin/RoleManager";
    }

    /*
    角色管理
     */
    @RequestMapping(value = "/addRole",method = RequestMethod.GET)
    public String addRole()
    {
        return "/admin/role/addRole";
    }
    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public void addRole(HttpServletRequest request,HttpServletResponse response) throws IOException {
        try{
            String rolename= (String) request.getParameter("role");
            Role role=new Role();
            role.setRolename(rolename);
            roleService.addRole(role);
            response.sendRedirect("/admin/roleManager");
        }
        catch (Exception ex)
        {

        }
    }
    @RequestMapping(value = "/delRole")
    public void delRole(HttpServletRequest request,HttpServletResponse response)
    {
        try {
            int id=Integer.parseInt(request.getParameter("id"));
            rolePermissionService.delRolePermissionByRoleId(id);
            roleUserService.delByRoleId(id);
            roleService.delRoleById(id);
            System.out.println("删除成功");
            response.sendRedirect("/admin/roleManager");
        } catch (IOException e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
    }




    /*
    角色管理界面
     */
    @RequestMapping(value = "/roleManager",method = RequestMethod.GET)
    public String roleManager(HttpServletRequest request)
    {
        Pager pager=null;
        pager=roleService.getRolePager();
        HttpSession session=request.getSession();
        session.setAttribute("pager",pager);
        return "/admin/RoleManager";
    }

    @RequestMapping(value = "/roleManager",method = RequestMethod.POST)
    public String roleManager()
    {
        return "/admin/RoleManager";
    }




    /*
    获取用户分配的角色
     */
    private Set getRolesForUser(int id)
    {
        List<RoleUser> roleUsers=roleUserService.selectByUserId(id);
        Set<Role> roles=new HashSet<Role>();
        for(RoleUser roleUser:roleUsers)
        {
            Role role=roleService.getRoleById(roleUser.getRoleId());
            roles.add(role);
        }
        return roles;
    }

    /*
    获取用户未分配的角色
     */
    private List<Role> getRolesNotForUser(int id)
    {
        List<Role> allRoles=roleService.getAllRole();
        Set<Role> rolesForUser=this.getRolesForUser(id);
        List<Role> roles=new ArrayList<Role>();
        Set<Role> set =rolesForUser;
        Set rolenameSet=new HashSet<String>();
        for(Role role:set){
            rolenameSet.add(role.getRolename());
        }
        for(Role role:allRoles)
        {
            String rolename=role.getRolename();
            if(rolenameSet.add(rolename))
            {
                roles.add(role);
            }

        }
        return roles;
    }

    /*
    获取角色分配的权利
     */
    private Set<Permission> getPermissionsForRole(int id)
    {
        List<RolePermission> RolePermissions=rolePermissionService.selectByRoleId(id);
        Set<Permission> permissions=new HashSet<Permission>();
        for(RolePermission rolePermission:RolePermissions)
        {
            Permission permission=permissionService.selectByPrimaryKey(rolePermission.getPermissionId());
            permissions.add(permission);
        }
        return permissions;
    }

    /*
    获取角色未分配的权利
     */

    private List<Permission> getPermissionNotForRole(int id)
    {
        List<Permission> allPermissions=permissionService.getAllPermission();
        Set<Permission> permissionssForRole=this.getPermissionsForRole(id);
        List<Permission> Permissions=new ArrayList<Permission>();
        Set<Permission> set =permissionssForRole;
        Set rolenameSet=new HashSet<String>();
        for(Permission permission:set){
            rolenameSet.add(permission.getPermission());
        }
        for(Permission permission:allPermissions)
        {
            String permissionName=permission.getPermission();
            if(rolenameSet.add(permissionName))
            {
                Permissions.add(permission);
            }

        }
        return Permissions;
    }

    /*
    权限管理
     */
    @RequestMapping(value = "/addPermission")
    public String addPermission(HttpServletRequest request,HttpSession session)
    {
        String permissionName=request.getParameter("permission");
        Permission permission=new Permission();
        permission.setPermission(permissionName);
        permissionService.addPermission(permission);
        ArrayList<Permission> permissions=permissionService.getAllPermission();
        session.setAttribute("permissions",permissions);
        return "/admin/PermissionManager";
    }
    @RequestMapping(value = "/PermissionManager")
    public String PermissionManager(HttpSession session)
    {
        ArrayList<Permission> permissions=permissionService.getAllPermission();
        session.setAttribute("permissions",permissions);
        return "/admin/PermissionManager";
    }

    //删除权利
    @RequestMapping(value = "/delPermission")
    public String delPermission(HttpServletRequest request,HttpSession session)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        rolePermissionService.delByPermissionId(id);
        permissionService.delPermissionById(id);
        ArrayList<Permission> permissions=permissionService.getAllPermission();
        session.setAttribute("permissions",permissions);
        return "/admin/PermissionManager";
    }
}
