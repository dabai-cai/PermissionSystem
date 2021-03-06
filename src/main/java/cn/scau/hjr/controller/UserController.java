package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {

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

    @RequestMapping("/showUser")
    public String showUser( Model model){
        log.info("查询所有用户信息");
       // List<User> userList = userService.getAllUser();
       // model.addAttribute("userList",userList);
        return "user/showUser";
    }

    @RequestMapping(value = {"/add"},method = RequestMethod.GET)
    public String addUser(Model model)
    {
        System.out.println("get");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = {"/add"},method =RequestMethod.POST)
    public String addUser(HttpServletRequest request)
    {
        System.out.println("post");
        String account= request.getParameter("account");
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

        if(userService.addUser(user))
        return "/user/success";
        else{
            return "/user/registerError";
        }
    }

    //跳转到登录界面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String userLogin()
    {
        return "user/login";
    }


    @RequestMapping(value={"/login"},method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request)
    {
        String password=request.getParameter("password");
        String account=request.getParameter("account");
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(account, password);
        try{
            subject.login(token);
            return "/admin/test";
        }catch(Exception e){
            e.printStackTrace();
            return "/user/error";
        }

    }

    //用户主页
    @RequestMapping(value = "/index")
    public String index()
    {
        return "user/index";
    }



    /*
    等下再来做，得到pager
    @RequestMapping(value="searchUser")
    public String searchUser(HttpServletRequest resquest)
    {
        String key=resquest.getParameter("searchUser");
        ArrayList<User> userList=userService.getSearchUser(key);
        resquest.setAttribute("searchUsers",userList);
        return "/user/index";
    }

   */



    @RequestMapping(value = "/Vippermission")
    public String ifVip(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("userId"));
        RoleUser roleUser=new RoleUser();
        roleUser.setUserId(id);
        roleUser.setRoleId(12);
        RoleUser roleUser1= roleUserService.selectByUserIdAndRoleId(roleUser);
        if(roleUser1==null)
        {
            return "/user/role/error";
        }
        else{
            ArrayList<Permission> permissions=new ArrayList<Permission>();
            ArrayList<RolePermission> rolePermissions=rolePermissionService.selectByRoleId(12);
            for(RolePermission rolePermission:rolePermissions)
            {
                Permission permission=permissionService.selectByPrimaryKey(rolePermission.getPermissionId());
                permissions.add(permission);
            }
            request.setAttribute("permissions",permissions);
            return "/user/role/vip";
        }

    }

    @RequestMapping(value = "/studentPermission")
    public String ifRob(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("userId"));
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(id);
        roleUser.setRoleId(17);
        RoleUser roleUser1 = roleUserService.selectByUserIdAndRoleId(roleUser);
        if (roleUser1 == null) {
            return "/user/role/error";
        } else {
            ArrayList<Permission> permissions = new ArrayList<Permission>();
            ArrayList<RolePermission> rolePermissions = rolePermissionService.selectByRoleId(17);
            for (RolePermission rolePermission : rolePermissions) {
                Permission permission = permissionService.selectByPrimaryKey(rolePermission.getPermissionId());
                permissions.add(permission);
            }
            request.setAttribute("permissions", permissions);
            return "/user/role/rob";
        }
    }

    //TeacherPermission
    @RequestMapping(value = "/TeacherPermission")
    public String ifTeacher(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("userId"));
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(id);
        roleUser.setRoleId(18);
        RoleUser roleUser1 = roleUserService.selectByUserIdAndRoleId(roleUser);
        if (roleUser1 == null) {
            return "/user/role/error";
        } else {
            ArrayList<Permission> permissions = new ArrayList<Permission>();
            ArrayList<RolePermission> rolePermissions = rolePermissionService.selectByRoleId(18);
            for (RolePermission rolePermission : rolePermissions) {
                Permission permission = permissionService.selectByPrimaryKey(rolePermission.getPermissionId());
                permissions.add(permission);
            }
            request.setAttribute("permissions", permissions);
            return "/user/role/teacher";
        }
    }





    @RequestMapping(value = "/update")
    public String userUpdate(HttpServletRequest request)
    {
        int id=Integer.parseInt(request.getParameter("userId"));
        User user0=new User();
        user0.setUserId(id);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int age=Integer.parseInt(request.getParameter("age"));
        String  phone=request.getParameter("phone");
        user0.setUsername(username);
        user0.setAge(age);
        user0.setPhone(phone);
        user0.setPassword(password);
        userService.updateByPrimaryKey(user0);
        User user=userService.selectByPrimaryKey(id);
        HttpSession session=request.getSession();
        session.setAttribute("user",user);//更新用户信息
        request.setAttribute("searchUsers",new ArrayList<User>());//防止jsp界面get到空指针
        return "/user/index";
    }

    @RequestMapping(value = "testModel")
    public String testModel(Model model)
    {
        ArrayList<User> users=userService.getAllUser();
        model.addAttribute("users",users);
        return "test";
    }


}
