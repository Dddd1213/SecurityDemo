package com.yangmao.service.impl;

import com.yangmao.domain.LoginUser;
import com.yangmao.domain.ResponseResult;
import com.yangmao.domain.User;
import com.yangmao.mapper.UserMapper;
import com.yangmao.properties.JwtProperties;
import com.yangmao.service.LoginService;
import com.yangmao.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

//    @Autowired
//    UserMapper userMapper;

    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(authenticate==null){
            throw new RuntimeException("登录失败");
        }
        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        Long id = loginUser.getUser().getId();
        Map<String, Object> claim = new HashMap<>();
        claim.put("ID",id.toString());
        String jwt = JwtUtil.createJWT(jwtProperties.getSecretKey(), jwtProperties.getTtl(), claim);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("token: ",jwt);

        redisTemplate.opsForValue().set("login:"+id,loginUser);

        return new ResponseResult(200,"登录成功",map);
    }

    @Override
    public ResponseResult logout() {

        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        redisTemplate.delete("login:"+id);
        return new ResponseResult(200,"注销成功");
    }
}
