package com.mliang.controller.login;

import com.alibaba.fastjson.JSONObject;
import com.mliang.model.User;
import com.mliang.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "/selectUserById", produces = "application/json; charset=utf-8",method = RequestMethod.GET)
    @ResponseBody
    public String selectUserById() throws Exception {
        log.info("用户查询");
        JSONObject json = new JSONObject();
        json.put("code","201");
        json.put("msg","查询失败");
        User user = userService.selectUser(1);
        if(user!=null){
            json.put("code","200");
            json.put("msg","查询成功");
        }
        return json.toJSONString();
    }


}