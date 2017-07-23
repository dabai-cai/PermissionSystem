package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import cn.scau.hjr.util.shiroUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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

    //个人信息
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String profile(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("userId"));
        User user=userService.selectByPrimaryKey(id);
        request.setAttribute("user",user);
        return "/admin/my-profile";
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
        return "/admin/users";
    }
    //用户添加
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public void addUser(HttpServletRequest request)
    {
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        String username=request.getParameter("username");
        String sex=request.getParameter("sex");

         String  phone=request.getParameter("phone");
        int age=Integer.parseInt(request.getParameter("age"));
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setSex(sex);
        user.setPhone(phone);
        user.setAge(age);
        user.setUsername(username);
        userService.addUser(user);
    }

    //删除用户
    @RequestMapping(value = "/delUser")
    public String delUser(HttpServletRequest request, RedirectAttributes redirectAttributes)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        userService.delUserById(id);
        roleUserService.delByUserId(id);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/users";
    }
//更新用户
    @RequestMapping(value = "/updateUser")
    public String userUpdate(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        User user0=new User();
        User temp=userService.selectByPrimaryKey(id);//为了获取账号来加密密码
        user0.setUserId(id);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        password= shiroUtil.encode(password,temp.getAccount());//加密账号
        int age=Integer.parseInt(request.getParameter("age"));
        String phone=request.getParameter("phone");
        String sex=request.getParameter("sex");
        user0.setSex(sex);
        user0.setUsername(username);
        user0.setAge(age);
        user0.setPhone(phone);
        user0.setPassword(password);
        userService.updateByPrimaryKey(user0);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/index")
    public String toIndex()
    {
        return "/admin/index";
    }


    @RequestMapping(value = "/roleUser")
    public String  roleUser(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        String[] roles=request.getParameterValues("role");
        int userId=Integer.parseInt(request.getParameter("userId"));
        roleUserService.delByUserId(userId);//删除用户拥有的角色
        if(roles!=null)
        {
            for(String rolename:roles)//重新关联用户和角色
            {
                RoleUser roleUser=new RoleUser();
                Role role=roleService.getRoleByRolename(rolename);
                roleUser.setRoleId(role.getRoleId());
                roleUser.setUserId(userId);
                roleUserService.insert(roleUser);
            }
        }
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/users";
    }



    /****************************    角色管理    **********************************************/
//获取用户分配和未分配角色

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(HttpServletRequest request) throws IOException {
        try{
            String rolename= (String) request.getParameter("rolename");
            String describe=(String )request.getParameter("aboutRole");
            Role role=new Role();
            role.setRolename(rolename);
            role.setAboutRole(describe);
            roleService.addRole(role);;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return "redirect:/admin/roles";
    }
    //删除角色
    @RequestMapping(value = "/delRole")
    public String delRole(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
            int id=Integer.parseInt(request.getParameter("id"));
            rolePermissionService.delRolePermissionByRoleId(id);
            roleUserService.delByRoleId(id);
            roleService.delRoleById(id);
            int pageindex=Integer.parseInt(request.getParameter("pageindex"));
            redirectAttributes.addAttribute("pageindex",pageindex);
            return "redirect:/admin/roles";
    }

    //更新角色
    @RequestMapping(value = "/updateRole")
    public String  updateRole(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        int id=Integer.parseInt(request.getParameter("id"));
        String rolename=request.getParameter("rolename");
        String describe=request.getParameter("describe");
        Role role=new Role();
        role.setAboutRole(describe);
        role.setRolename(rolename);
        role.setRoleId(id);
        roleService.updateRole(role);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/roles";
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
        return "/admin/roles";
    }


    @RequestMapping(value = "/rolePermission")
    public String rolePermission(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        String[] permissions=request.getParameterValues("permission");
        int id=Integer.parseInt(request.getParameter("roleId"));
        rolePermissionService.delRolePermissionByRoleId(id);
        if(permissions!=null)
        {
            for(String permissionName:permissions)
            {
                Permission permission=permissionService.selectByPermissionName(permissionName);
                RolePermission rolePermission=new RolePermission();
                rolePermission.setPermissionId(permission.getPermissionId());
                rolePermission.setRoleId(id);
                rolePermissionService.insert(rolePermission);
            }
        }
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/roles";
    }

  /**************************************   权限管理  ********************************************/
    //添加权限
    @RequestMapping(value = "/addPermission")
    public String  addPermission(HttpServletRequest request)
    {
        String permissionName=request.getParameter("permission");
        String url=request.getParameter("url");
        Permission permission=new Permission();
        permission.setPermission(permissionName);
        permission.setUrl(url);
        permissionService.addPermission(permission);
        return "redirect:/admin/PermissionManager";
    }
    //获取权限信息
    @RequestMapping(value = "/PermissionManager",method = RequestMethod.GET)
    public String PermissionManager(HttpServletRequest request,Model model)
    {
        Pager pager=permissionService.getPermissionPager();
        model.addAttribute("pager",pager);
        return "/admin/permissions";
    }

    //删除权利
    @RequestMapping(value = "/delPermission")
    public String delPermission(HttpServletRequest request,RedirectAttributes redirectAttributes)
    {
        int id=Integer.parseInt(request.getParameter("id"));
        rolePermissionService.delByPermissionId(id);
        permissionService.delPermissionById(id);
        int pageindex=Integer.parseInt(request.getParameter("pageindex"));
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/PermissionManager";
    }
    //更新权限
    @RequestMapping(value = "/updatePermission")
    public String updatePermission(HttpServletRequest request,RedirectAttributes redirectAttributes)
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
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/PermissionManager";
    }
}
