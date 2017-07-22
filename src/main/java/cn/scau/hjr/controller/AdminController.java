package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import cn.scau.hjr.util.shiroUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    /**********************************  用户管理 ******************************************/
    //用户添加
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

    //删除用户
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
            response.sendRedirect("/admin/users?pageindex="+pageindex);
        }catch (Exception ex)
        {

        }

    }
//更新用户
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
        User temp=userService.selectByPrimaryKey(id);//为了获取账号来加密密码
        user0.setUserId(id);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        password= shiroUtil.encode(password,temp.getAccount());
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
        try{
            response.sendRedirect(request.getContextPath()+"/admin/users?pageindex="+pageindex);

        }catch (Exception e)
        {
           e.printStackTrace();
        }
    }

    @RequestMapping(value = "/index")
    public String toIndex()
    {
        return "/BackGround/index";
    }


//角色分配
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
        return "/BackGround/users";
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
        return "/BackGround/users";
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
        return "/BackGround/roles";
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

        return "/BackGround/roles";
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
            String rolename= (String) request.getParameter("rolename");
            String describe=(String )request.getAttribute("roledescribe\t");
            Role role=new Role();
            role.setRolename(rolename);
            role.setAboutRole(describe);
            roleService.addRole(role);
            response.sendRedirect("/admin/roles");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.out.println("角色添加出错");
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
            response.sendRedirect("/admin/roles");
        } catch (IOException e) {
            System.out.println("删除失败");
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/updateRole")
    public void updateRole(HttpServletRequest request,HttpServletResponse response)
    {
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        int id=Integer.parseInt(request.getParameter("id"));
        String rolename=request.getParameter("rolename");
        String describe=request.getParameter("describe");
        Role role=new Role();
        role.setAboutRole(describe);
        role.setRolename(rolename);
        role.setRoleId(id);
        System.out.println(rolename);
        System.out.println(describe);
        System.out.println(id);
        roleService.updateRole(role);
        try {
            response.sendRedirect("/admin/roles?pageindex="+pageindex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /*
    角色管理界面
     */
    @RequestMapping(value = "/roles",method = RequestMethod.GET)
    public String roleManager(HttpServletRequest request,Model model)
    {
        Pager pager=null;
        pager=roleService.getRolePager();
        model.addAttribute("pager",pager);
        return "/BackGround/roles";
    }

    @RequestMapping(value = "/roles",method = RequestMethod.POST)
    public String roleManager()
    {
        return "/BackGround/roles";
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
    //添加权限
    @RequestMapping(value = "/addPermission")
    public void addPermission(HttpServletRequest request,HttpSession session,HttpServletResponse response)
    {
        String permissionName=request.getParameter("permission");
        String url=request.getParameter("url");
        Permission permission=new Permission();
        permission.setPermission(permissionName);
        permission.setUrl(url);
        permissionService.addPermission(permission);
        try {
            response.sendRedirect("/admin/PermissionManager");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //获取权限信息
    @RequestMapping(value = "/PermissionManager",method = RequestMethod.GET)
    public String PermissionManager(HttpServletRequest request,Model model)
    {
        Pager pager=permissionService.getPermissionPager();
        model.addAttribute("pager",pager);
        return "/BackGround/permissions";
    }

    //删除权利
    @RequestMapping(value = "/delPermission")
    public void delPermission(HttpServletRequest request,HttpSession session,HttpServletResponse response)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        rolePermissionService.delByPermissionId(id);
        permissionService.delPermissionById(id);
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        try {
            response.sendRedirect("/admin/PermissionManager?pageindex="+pageindex);
        } catch (IOException e) {

e.printStackTrace();
        }
    }
    //更新权限
    @RequestMapping(value = "/updatePermission")
    public void updatePermission(HttpServletRequest request,HttpServletResponse response)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        String permissionName=request.getParameter("permissionName");
        String url=request.getParameter("url");
        Permission permission=new Permission();
        permission.setUrl(url);
        permission.setPermission(permissionName);
        permission.setPermissionId(id);
        permissionService.updateByPrimaryKey(permission);
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        try{
            response.sendRedirect("/admin/PermissionManager?pageindex="+pageindex);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String profile(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("userId"));
        User user=userService.selectByPrimaryKey(id);
        request.setAttribute("user",user);
        return "/BackGround/my-profile";
    }



    //用户管理
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String users(HttpServletRequest request, HttpSession session, Model model )
    {
            Pager pager=null;
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
            model.addAttribute("pager",pager);
            return "/BackGround/users";
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String users()
    {
        return "/BackGround/users";
    }


}
