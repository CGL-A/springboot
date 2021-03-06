package com.chbtc.springboot.controller;

import com.chbtc.springboot.model.User;
import com.chbtc.springboot.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chbtc on 2017/5/16.
 */
@Controller
@RequestMapping("/user")
public class FirstController {
    private static final Logger logger = LoggerFactory.getLogger(FirstController.class);
    @Autowired
    private IUserService userService;
    @RequestMapping("/{id}")
    @ResponseBody
    public User testSpringBoot(@PathVariable("id") Integer id) {
         User user=null;
         user=userService.getUserById(id);
        System.out.println(user);
        return user;
    }
    @RequestMapping(value="/login",method = RequestMethod.GET)
    public String login()
    {
        return  "login";
    }
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public String login(String name,String password) {

        UsernamePasswordToken token=new UsernamePasswordToken(name,password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
            logger.info("对用户[" + name + "]进行登录验证..验证开始");
            currentUser.login(token);
            logger.info("对用户[" + name + "]进行登录验证..验证通过");
        }catch(UnknownAccountException uae){
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,未知账户");
           // redirectAttributes.addFlashAttribute("message", "未知账户");
        }catch(IncorrectCredentialsException ice){
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误的凭证");
            //redirectAttributes.addFlashAttribute("message", "密码不正确");
        }catch(LockedAccountException lae){
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,账户已锁定");
            //redirectAttributes.addFlashAttribute("message", "账户已锁定");
        }catch(ExcessiveAttemptsException eae){
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,错误次数过多");
            //redirectAttributes.addFlashAttribute("message", "用户名或密码错误次数过多");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            logger.info("对用户[" + name + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
           // redirectAttributes.addFlashAttribute("message", "用户名或密码不正确");
        }
        //验证是否登录成功
        if(currentUser.isAuthenticated()){
            Session session = SecurityUtils.getSubject().getSession();
            //session.setAttribute("loginType",LoginEnum.ADMIN.toString());
            logger.info("用户[" + name + "]登录认证通过(这里可以进行一些认证通过后的一些系统参数初始化操作)");
           //String ip = IpUtil.getIpAddr(request);
           // logService.insertLoginLog(username, ip, request.getContextPath());
            return "redirect:/hello";
        }else{
            token.clear();
            return "redirect:/login";
        }
    }

    @RequestMapping("/add/{name}")
    @ResponseBody

    public User add(@PathVariable("name") String name) {
        User user=new User();
        user.setUsername(name);
        user.setPassword("123456");
        userService.insert(user);
       // user.setPassword("ggsss");
        System.out.println(user);
        return user;
    }
    @RequestMapping("/hello")
    public String hello(ModelMap  map) {
        map.addAttribute("name","cgls");
        map.addAttribute("names","cgl1");
        return "hello";
    }
}
