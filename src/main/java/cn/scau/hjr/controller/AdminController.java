package cn.scau.hjr.controller;

import cn.scau.hjr.model.Pager;
import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import cn.scau.hjr.service.RoleService;
import cn.scau.hjr.service.RoleUserService;
import cn.scau.hjr.service.UserService;
import org.apache.log4j.Logger;
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


    /*
    用户管理
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public String addUser()
    {
        return "/admin/addUser";
    }
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(HttpServletRequest request)
    {
        System.out.println("post");
        int account=Integer.parseInt(request.getParameter("account"));
        String password=request.getParameter("password");
        String username=request.getParameter("username");

        String sex=request.getParameter("sex");
        int phone=Integer.parseInt(request.getParameter("phone"));
        int age=Integer.parseInt(request.getParameter("age"));
        User user=new User();
        user.setAccount(account);

        user.setPassword(password);

        user.setSex(sex);

        user.setPhone(phone);

        user.setAge(age);


        user.setUsername(username);

        if(userService.addUser(user))
            return "/BackGround/index";
        else{
            return "/admin/addUserError";
        }
    }

    @RequestMapping(value = "/userManager",method = RequestMethod.GET)
    public String userManager(HttpServletRequest request)
    {
        Pager pager=null;
        pager=userService.getUserPager();
        HttpSession session=request.getSession();
        session.setAttribute("pager",pager);
        System.out.println("pager:"+pager);
        return "/admin/UserManager";
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
        userService.delUserById(id);
        roleUserService.delByUserId(id);
        System.out.println("删除成功"+id);
        try{
            response.sendRedirect("/admin/userManager");
        }catch (Exception ex)
        {

        }

    }

    @RequestMapping(value="searchUser")
    public String searchUser(HttpServletRequest resquest)
    {
        String key=resquest.getParameter("searchUser");
        ArrayList<User> userList=userService.getSearchUser(key);
        resquest.setAttribute("searchUsers",userList);
        return "/admin/SearchUser";
    }

    @RequestMapping(value = "/index")
    public String toIndex()
    {
        return "/BackGround/index";
    }


    @RequestMapping(value = "update")
    public String update(HttpServletRequest request)
    {
        int userId=Integer.parseInt(request.getParameter("id"));
        return "/admin/UserManager";
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
            response.sendRedirect("/admin/RoleManager");
        }
        catch (Exception ex)
        {

        }
    }
    @RequestMapping(value = "/RoleManager")
    public String roleIndex()
    {
        return "/admin/RoleManager";
    }


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

}
