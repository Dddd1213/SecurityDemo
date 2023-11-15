package com.yangmao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yangmao.domain.LoginUser;
import com.yangmao.domain.User;
import com.yangmao.mapper.MenuMapper;
import com.yangmao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UsersDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>().eq(User::getUserName, s);
        User user = userMapper.selectOne(queryWrapper);
        if(user==null){
            throw new RuntimeException("用户名或者密码错误");
        }

        //查询权限信息
//        ArrayList<String> list = new ArrayList<>(Arrays.asList("test","admin"));

        List<String> list = menuMapper.selectPermsById(user.getId());

        //把数据封装成UserDetails返回
        return new LoginUser(user,list);
    }
}
