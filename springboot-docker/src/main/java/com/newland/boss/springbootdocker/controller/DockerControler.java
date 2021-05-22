package com.newland.boss.springbootdocker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ${linfengpeng}
 * @Title: DockerControoler
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/11/10 18:15
 */
@RestController
public class DockerControler {

    @RequestMapping("/docker")
    public String test(){
       return "Docker!!!!!!!!!!!!!";
    }
}
