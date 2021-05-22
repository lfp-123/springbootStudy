package com.newland.oauth2.springbootoauth2server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author lfp
 * @Title: UserController
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 14:25
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal getCurrentUser(Principal principal){
        return principal;
    }
}
