package com.newland.boss.springbootoauth2client1.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author lfp
 * @Title: HelloController
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 14:38
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName()+ Arrays.toString(authentication.getAuthorities().toArray());
    }
}
