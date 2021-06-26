package com.mliang.controller;

import com.alibaba.fastjson.JSONObject;
import com.mliang.model.User;
import com.mliang.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/select", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    public ModelAndView selectUser() throws Exception {
        log.info("用户查询");
        ModelAndView mv = new ModelAndView();
        User user = userService.selectUser(1);
        mv.addObject("user", user);
        mv.setViewName("user");
        log.info("查询用户信息成功");
        return mv;
    }


    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveUser(@RequestBody User user) throws Exception {
        log.info("执行用户保存.....");
        JSONObject json = new JSONObject();
        json.put("code","201");
        json.put("msg","插入失败");
        String userId = UUID.randomUUID().toString();
        Date createTime = new Date(System.currentTimeMillis());
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setId(userId);
        user.setPassword(md5Password);
        user.setCreateTime(createTime);
        try {
            int countExist = userService.selectUserByUserName(user.getUsername());
            if(countExist>0){
                json.put("msg","姓名已被注册！");
                return json.toJSONString();
            }
            int result = userService.saveUser(user);
            if(result>0){
                json.put("code","200");
                json.put("msg","插入成功");
            }
        } catch (Exception e) {
            log.error("执行保存用户异常：原因如下："+e.getMessage());
        }
        return json.toJSONString();
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String logintUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) throws Exception {
        log.info("用户登陆接口，当前登录人："+user.getUsername());
        JSONObject json = new JSONObject();
        json.put("code","201");
        String currentPassword =  DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        User dataBaseUser = getUserByPassword(user);
        String msg = "登录失败";
        if(dataBaseUser==null){
            log.info("用户账号不存在！");
            msg = "用户账号不存在！";
        }else if(!currentPassword.equals(dataBaseUser.getPassword())){
            log.info("用户密码错误！");
            msg = "用户密码错误！";
        }else {
            json.put("code","200");
            msg = "登录成功";
            HttpSession session = request.getSession(false);
            if(session!=null){
                //注销原session，为的是重置sessionId
                session.invalidate();
                //将临时map中的数据转移至新session

            }
            session = request.getSession();
            session.setAttribute("user",user);
            json.put("sessionId",session.getId());
            json.put("data",dataBaseUser);
            Cookie cookie=new Cookie("JSESSIONID", session.getId());
            cookie.setPath("/");
            response.addCookie(cookie);

        }
        json.put("msg",msg);
        log.info(msg);
        return json.toJSONString();
    }

    private User getUserByPassword(User user) throws Exception {
        log.info("根据用户的账号密码查询用户是否存在");
        Boolean bo = false;
        User dataBaseUser = userService.selectUserByPassword(user);
        return dataBaseUser;
    }
}