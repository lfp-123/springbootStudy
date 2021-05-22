package com.newland.boss.springboottoken.JwtUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newland.boss.springboottoken.pojo.Constant;
import com.newland.boss.springboottoken.pojo.User;
import com.newland.boss.springboottoken.service.UserService;
import com.newland.boss.springboottoken.util.MD5Util;
import com.newland.boss.springboottoken.util.MybaisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lfp
 * @Title: JwtUtil
 * @ProjectName Springboot
 * @Description: TODO
 * @date 2020/12/15 16:00
 */
@Component
public class JwtUtil  {

    public String createToken(User user){
        Constant.getMaps().put(user.getId(),user);
        String userjson = JSONObject.toJSONString(user);
        String token = MybaisUtil.decryptDecode(userjson);
        return token;
    }

    /**
     * 验证token是否有效
     * @param token  请求头中携带的token
     * @return  token验证结果  2-token过期；1-token认证通过；0-token认证失败
     */
    public boolean verify(String token){
        String tokenJ = MybaisUtil.encryptionDecode(token);
        User user = JSONObject.parseObject(tokenJ, User.class);
        //1 验证是否存在于map (大型项目可使用redis)
        User mapuser = Constant.getMaps().get(user.getId());
        if(mapuser == null){
            return false;
        }else{
            return true;
        }
        //2 是否密码正确  此时省略

        //2 验证是否超时

    }
}
