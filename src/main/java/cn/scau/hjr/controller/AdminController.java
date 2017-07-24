package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


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
    public String users(String keyName,Model model)
    {
        Pager pager=null;
        if(keyName==null||keyName=="") {
            model.addAttribute("keyName",null);
            pager=userService.getUserPager();
        }
        else{
                model.addAttribute("keyName",keyName);
                pager=userService.getSearchPager(keyName);
        }
        model.addAttribute("pager",pager);
        return "/admin/users";
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String users(Model model)
    {
        Pager pager=userService.getUserPager();
        model.addAttribute("keyName","");
        model.addAttribute("pager",pager);
        return "/admin/users";
    }
    //用户添加
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ResponseBody
    public void addUser(User user)
    {
        userService.addUser(user);
    }

    //删除用户
    @RequestMapping(value = "/delUser")
    public String delUser(Integer id,Integer pageindex,RedirectAttributes redirectAttributes,String keyName)
    {
        userService.delUserById(id);
        roleUserService.delByUserId(id);
        redirectAttributes.addAttribute("pageindex",pageindex);
        if(keyName!=null||keyName!="") {
            redirectAttributes.addAttribute("keyName",keyName);
        }
        return "redirect:/admin/users";
    }
//更新用户
    @RequestMapping(value = "/updateUser")
    public String userUpdate(User user,Integer pageindex,RedirectAttributes redirectAttributes,String keyName)
    {

        userService.updateByPrimaryKey(user);
        redirectAttributes.addAttribute("pageindex",pageindex);
        if(keyName!=null||keyName!="")
        {
            redirectAttributes.addAttribute("keyName",keyName);
        }
        return "redirect:/admin/users";
    }

    //后台管理首页
    @RequestMapping(value = "/index")
    public String toIndex()
    {
        return "/admin/index";
    }


    //分配角色
    @RequestMapping(value = "/roleUser")
    public String  roleUser(String[] role,Integer userId,Integer pageindex,RedirectAttributes redirectAttributes,String keyName)
    {
        roleUserService.delByUserId(userId);//删除用户拥有的角色
        if(role!=null)
        {
            for(String rolename:role)//重新关联用户和角色
            {
                RoleUser roleUser=new RoleUser();
                Role temp=roleService.getRoleByRolename(rolename);
                roleUser.setRoleId(temp.getRoleId());
                roleUser.setUserId(userId);
                roleUserService.insert(roleUser);
            }
        }
        if(keyName!=null||keyName!="") {
            redirectAttributes.addAttribute("keyName",keyName);
        }
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/users";
    }



    /****************************    角色管理    **********************************************/
//获取用户分配和未分配角色

    @RequestMapping(value = "/addRole",method = RequestMethod.POST)
    public String addRole(Role role)  {
            roleService.addRole(role);;
        return "redirect:/admin/roles";
    }
    //删除角色
    @RequestMapping(value = "/delRole")
    public String delRole(Integer id,Integer pageindex,RedirectAttributes redirectAttributes)
    {
            rolePermissionService.delRolePermissionByRoleId(id);
            roleUserService.delByRoleId(id);
            roleService.delRoleById(id);
            redirectAttributes.addAttribute("pageindex",pageindex);
            return "redirect:/admin/roles";
    }

    //更新角色
    @RequestMapping(value = "/updateRole")
    public String  updateRole(Integer pageindex,Role role,RedirectAttributes redirectAttributes)
    {
        roleService.updateRole(role);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/roles";
    }

    /*
    角色管理界面
     */
    @RequestMapping(value = "/roles",method = RequestMethod.GET)
    public String roleManager(Model model)
    {
        Pager pager=null;
        pager=roleService.getRolePager();
        model.addAttribute("pager",pager);
        return "/admin/roles";
    }


    //分配权限
    @RequestMapping(value = "/rolePermission")
    public String rolePermission(String[] permission, Integer roleId,Integer pageindex,RedirectAttributes redirectAttributes)
    {
        rolePermissionService.delRolePermissionByRoleId(roleId);
        if(permission!=null)
        {
            for(String permissionName:permission)
            {
                Permission p=permissionService.selectByPermissionName(permissionName);
                RolePermission rolePermission=new RolePermission();
                rolePermission.setPermissionId(p.getPermissionId());
                rolePermission.setRoleId(roleId);
                rolePermissionService.insert(rolePermission);
            }
        }
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/roles";
    }

  /**************************************   权限管理  ********************************************/
    //添加权限
    @RequestMapping(value = "/addPermission")
        public String  addPermission(Permission permission,Model model)
        {

            permissionService.addPermission(permission);
            return "redirect:/admin/PermissionManager";
    }
    //获取权限信息
    @RequestMapping(value = "/PermissionManager",method = RequestMethod.GET)
    public String PermissionManager(Model model)
    {
        model.addAttribute("errorMsg","error");
        Pager pager=permissionService.getPermissionPager();
        model.addAttribute("pager",pager);
        return "/admin/permissions";
    }

    //删除权利
    @RequestMapping(value = "/delPermission")
    public String delPermission(Integer id,Integer pageindex,RedirectAttributes redirectAttributes)
    {
        rolePermissionService.delByPermissionId(id);
        permissionService.delPermissionById(id);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/PermissionManager";
    }
    //更新权限
    @RequestMapping(value = "/updatePermission")
    public String updatePermission(Permission permission,RedirectAttributes redirectAttributes,Integer pageindex)
    {
        permissionService.updateByPrimaryKey(permission);
        redirectAttributes.addAttribute("pageindex",pageindex);
        return "redirect:/admin/PermissionManager";
    }
}
