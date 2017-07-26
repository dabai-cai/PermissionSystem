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
    public String userLogin(String password,String account,Model model)
    {

        password=shiroUtil.encode(password,account);//shiro MD5 加密匹配
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(account, password);
        try{
            subject.login(token);///shiro身份验证
            User user=userService.getUserByAccount(account);
            model.addAttribute("currentUser",user);//配置当前用户
            return "redirect:/admin/index";
        }catch(Exception e){
            e.printStackTrace();
            return "/user/error";
        }
    }


    //个人信息
    @RequestMapping(value ="profile",method = RequestMethod.GET)
    public String profile(Model model)
    {
        Subject subject=SecurityUtils.getSubject();
        Session session=subject.getSession();
        User user=(User)session.getAttribute("currentUser");
        model.addAttribute("currentUser",user);
        return "/admin/my-profile";
    }

    @RequestMapping(value = "/test")
    public String test()
    {
        return "";
    }


}
