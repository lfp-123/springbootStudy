package cn.nit.controller;

import cn.nit.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ${林锋鹏}
 * @Title: UserController
 * @ProjectName Java
 * @Description: TODO
 * @date 2020/2/8 11:07
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/rep")
    public String testResp(Model model){
        System.out.println("执行了resp方法");
        User user = new User();
        user.setAge("21");
        user.setUsername("美美");
        user.setPassword("234");
        model.addAttribute("user",user);
        return "pages/success";
    }

    @RequestMapping("ajaxTest")
    @ResponseBody
    public User  testAjax(User user){
        //前端发送ajax的请求，传的是json字符串，后端自动把json封装成到对象
        System.out.println("执行Ajax请求");
        System.out.println(user);
        user.setUsername("111");
        user.setPassword("222");
        user.setAge("111");
        return  user;
    }
}
