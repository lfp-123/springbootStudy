package com.newland.boss.springboottoken.config;





import com.newland.boss.springboottoken.JwtUtil.JwtUtil;
import com.newland.boss.springboottoken.pojo.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;

/**
 * @author lfp
 * @Title: ServiceFilter
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:07
 */
@Component
public class ServiceFilter implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        System.out.println(contextPath);
        if(contextPath.equals(Constant.req)){
            return true;
        }
        String userInfo;
        if((userInfo = request.getHeader("token"))!= null){
           return jwtUtil.verify(userInfo);
        }
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
