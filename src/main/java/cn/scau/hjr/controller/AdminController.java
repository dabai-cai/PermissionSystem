package cn.scau.hjr.controller;

import cn.scau.hjr.model.Pager;
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
        request.setAttribute("pager",pager);
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
        System.out.println("删除成功"+id);
        try{
            response.sendRedirect("/admin/userManager");
        }catch (Exception ex)
        {

        }

    }

}
