package com.newland.boss.springboottoken.controller;

import com.alibaba.fastjson.JSONObject;
import com.newland.boss.springboottoken.JwtUtil.JwtUtil;
import com.newland.boss.springboottoken.pojo.Constant;
import com.newland.boss.springboottoken.pojo.User;
import com.newland.boss.springboottoken.service.UserService;
import com.newland.boss.springboottoken.util.MybaisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lfp
 * @Title: LoginController
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:48
 */
@RestController
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;
    //登录
    @PostMapping("/login")
    public String  login(@RequestBody User user){
        System.out.println(user);
        User userForBase=userService.findByUsername(user);
        if(userForBase == null){
            return "不存在此用户";
        }else {
            //存储token
            Constant.getMaps().put(userForBase.getId(),userForBase);
            String s = JSONObject.toJSONString(userForBase);
            String s1 = MybaisUtil.encryptionDecode(s);
            return s1;
        }
    }

    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
