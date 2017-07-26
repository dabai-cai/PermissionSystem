package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    //用户管理
    @RequestMapping(value = {"/users"},method = RequestMethod.GET)
    public String users(String keyName,Model model,String success,String repeat) {
        Pager pager=null;
        if(keyName==null||keyName=="") {//检测是否有搜索用户，没有则返回普通用户信息
            model.addAttribute("keyName",null);
            pager=userService.getUserPageByRole();
        }
        else{
                model.addAttribute("keyName",keyName);
                pager=userService.getSearchUserPageByRole(keyName,17);
        }
        if(success!=null&&success!=""){//用户添加是否成功
            model.addAttribute("errorMsg","User add success!");
        }if(repeat!=null&&repeat!=""){//用户是否早已存在
        model.addAttribute("errorMsg","User Already Exits!");
    }
        model.addAttribute("pager",pager);
        return "/admin/users";
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public String users(Model model) {//用户重置搜索条件
        Pager  pager=userService.getUserPageByRole();
        model.addAttribute("keyName","");
        model.addAttribute("pager",pager);
        return "/admin/users";
    }
    //用户添加
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(User user,RedirectAttributes redirectAttributes)
    {
        if(userService.addUser(user)) {
            redirectAttributes.addAttribute("success","true");
        }
        else{
            redirectAttributes.addAttribute("repeat","true");
        }
        return "redirect:/admin/users";
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
        if(keyName!=null||keyName!="") {
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
    public String addRole(Role role,RedirectAttributes redirectAttributes)  {
            if(!roleService.addRole(role)) {//验证角色是否重复添加了，添加就验证失败
                redirectAttributes.addAttribute("repeat","true");
            }else{
                redirectAttributes.addAttribute("success","true");//添加成功，返回成功信息给前端
            }
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
    public String roleManager(Model model,String repeat,String success)
    {
        Pager pager=null;
        pager=roleService.getRolePager();
        System.out.println("repeat:"+repeat);
        if(repeat!=null){//角色重复添加啦
            model.addAttribute("errorMsg",new String("Role already exists!"));
        }
        if(success!=null){//角色添加成功啦
            model.addAttribute("errorMsg",new String("Role add success!"));
        }
        model.addAttribute("pager",pager);
        return "/admin/roles";
    }


    //分配权限
    @RequestMapping(value = "/rolePermission")
    public String rolePermission(String[] permission, Integer roleId,Integer pageindex,RedirectAttributes redirectAttributes)
    {
        rolePermissionService.delRolePermissionByRoleId(roleId);
        if(permission!=null) {
            for(String permissionName:permission) {
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
        public String  addPermission(Permission permission,RedirectAttributes redirectAttributes)
        {
            if(!permissionService.addPermission(permission)){//判断是否重复添加,重复则告知前端
                redirectAttributes.addAttribute("repeat","true");
            }else{
                redirectAttributes.addAttribute("success","true");
            }
            return "redirect:/admin/PermissionManager";
    }
    //获取权限信息
    @RequestMapping(value = "/PermissionManager",method = RequestMethod.GET)
    public String PermissionManager(Model model,String repeat,String success)
    {
        if(repeat!=null) {
            model.addAttribute("validateMsg","Permission Already Exist!");//告知前端添加失败，重复添加了
        }
        if(success!=null) {
            model.addAttribute("validateMsg","Permission Add Success!");//成功添加用户
        }
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
