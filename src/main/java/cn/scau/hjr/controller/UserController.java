package cn.scau.hjr.controller;

import cn.scau.hjr.model.*;
import cn.scau.hjr.service.*;
import cn.scau.hjr.util.shiroUtil;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    //跳转到登录界面
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String userLogin()
    {
        return "user/login";
    }


    @RequestMapping(value={"/login"},method = RequestMethod.POST)
    public String userLogin(HttpServletRequest request, HttpServletResponse response)
    {
        String password=request.getParameter("password");
        String account=request.getParameter("account");
        password=shiroUtil.encode(password,account);//shiro MD5 加密匹配
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(account, password);
        try{
            subject.login(token);///shiro身份验证
            User user=userService.getUserByAccount(account);
            HttpSession session=request.getSession();
            session.setAttribute("currentUser",user);//配置当前用户
            response.sendRedirect("/admin/index");
        }catch(Exception e){
            e.printStackTrace();
            return "/user/error";
        }
      return "/user/error";
    }

    @RequestMapping(value = "test")
    public void testModel(Model model)
    {
        ArrayList<User> users=userService.getAllUser();
        for(User user:users)
        {
            user.setPassword(shiroUtil.encode(user.getPassword(),user.getAccount()));
            userService.updateByPrimaryKey(user);
        }
    }


}
