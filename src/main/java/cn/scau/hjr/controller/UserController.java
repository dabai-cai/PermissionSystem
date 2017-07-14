package cn.scau.hjr.controller;

import cn.scau.hjr.model.Role;
import cn.scau.hjr.model.RoleUser;
import cn.scau.hjr.model.User;
import cn.scau.hjr.service.RoleService;
import cn.scau.hjr.service.RoleUserService;
import cn.scau.hjr.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Zhangxq on 2016/7/15.
 */

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

        int account=Integer.parseInt(request.getParameter("account"));
        String password=request.getParameter("password");
        User user=new User();
        user.setAccount(account);
        user.setPassword(password);
        if(!userService.loginChk(user))
        {
            return "user/error";
        }
        else{
            //判断是否为管理员
            Role role=this.roleService.getRoleByRolename("root");
            RoleUser roleUser=null;
            RoleUser temp=new RoleUser();
            user=userService.getUser(user);//获取用户信息
            temp.setRoleId(role.getRoleId());
            temp.setUserId(user.getUserId());
            roleUser=roleUserService.selectByUserIdAndRoleId(temp);
            if(roleUser!=null)
            {
                //后台管理界面
                HttpSession session=request.getSession();
                session.setAttribute("rootuser",user);//管理员布置到session
                return "/BackGround/index";
            }
            else{
                //普通用户
                return "user/success";
            }
        }
    }

    @RequestMapping(value = "/test")
    public String testHtml()
    {
        return "/BackGround/index";
    }

}
