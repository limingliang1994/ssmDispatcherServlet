package com.mliang.controller;

import com.alibaba.fastjson.JSONObject;
import com.mliang.model.User;
import com.mliang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.UUID;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/select", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public ModelAndView selectUser() throws Exception {
        System.out.println("我在中间被执行.....");
        ModelAndView mv = new ModelAndView();
        User user = userService.selectUser(1);
        mv.addObject("user", user);
        mv.setViewName("user");
        return mv;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveUser() throws Exception {
        System.out.println("执行用户保存.....");
        JSONObject json = new JSONObject();
        json.put("code","201");
        json.put("msg","插入失败");
        String userId = UUID.randomUUID().toString();
        String name = "李四";
        String email = "2335113119@qq.com";
        String role = "master";
        Date createTime = new Date(System.currentTimeMillis());
        User user  = new User(userId,email,"15558287958",name,role,createTime);

        try {
            int result = userService.saveUser(user);
            if(result>0){
                json.put("code","200");
                json.put("msg","插入成功");
            }
        } catch (Exception e) {
            System.out.println("我遇到了异常");
        }
        return json.toJSONString();
    }
}