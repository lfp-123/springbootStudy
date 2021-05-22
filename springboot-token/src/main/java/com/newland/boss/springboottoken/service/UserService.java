package com.newland.boss.springboottoken.service;
import com.newland.boss.springboottoken.pojo.User;
import org.springframework.stereotype.Service;


/**
 * @author lfp
 * @Title: UserService
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:02
 */
@Service
public class UserService {


   public   User selectUserById(String id){

        User zhangsan = new User("123", "zhangsan");
        if(id.equals("123")){
            return zhangsan;
        }
        return null;
    }
    public User findByUsername(User user){
        User zhangsan = new User("123", "zhangsan");
        if(user.getId().equals("123")){
            return zhangsan;
        }
        return null;
    }
}
