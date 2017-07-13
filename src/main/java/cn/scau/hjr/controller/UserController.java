package cn.scau.hjr.controller;

import cn.scau.hjr.model.User;
import cn.scau.hjr.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zhangxq on 2016/7/15.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    private UserService userService;

    @RequestMapping("/showUser")
    public String showUser(HttpServletRequest request, Model model){
        log.info("查询所有用户信息");
       // List<User> userList = userService.getAllUser();
       // model.addAttribute("userList",userList);
        return "user/showUser";
    }
    @RequestMapping("/add")
    public String addUser(HttpServletRequest request)
    {
        User user=new User();
        user.setUsername("哈哈7944");
        user.setPassword("呵呵70055");
        user.setAccount(986576);
      userService.addUser(user);
        return "/user/success";
    }
    @RequestMapping(value={"login"})
    public String userLogin(HttpServletRequest request)
    {
        return "user/success";
    }

}
