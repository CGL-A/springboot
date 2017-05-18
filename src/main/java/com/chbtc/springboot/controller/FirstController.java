package com.chbtc.springboot.controller;

import com.chbtc.springboot.model.User;
import com.chbtc.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by chbtc on 2017/5/16.
 */
@Controller
@RequestMapping("/user")
public class FirstController {
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
