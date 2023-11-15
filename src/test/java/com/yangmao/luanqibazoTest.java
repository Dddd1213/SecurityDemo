package com.yangmao;

import com.yangmao.domain.User;
import com.yangmao.mapper.UserMapper;
import com.yangmao.properties.JwtProperties;
import com.yangmao.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


public class luanqibazoTest {
    @Autowired
    UserMapper userMapper;

    @Autowired
    JwtProperties jwtProperties;

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("123456", "$2a$10$l1GJoUIxyuQQt3Axx97cdu..wLccZZYxjkL7JyZcuZZxybXoY9Gam");
        System.out.println(matches);
    }


    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testJWT(){
        Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(),"eyJhbGciOiJIUzI1NiJ9.eyJJRCI6IjEiLCJleHAiOjE2OTk5NDc1NzJ9.PKDpi50dHUtx5RmDBp62F39LkppAo659YHXTBGjh40k");
        System.out.println(claims);
    }

    public static int removeDuplicates(int[] nums) {

        int k=0,flag=1;
        int[] de = new int[nums.length];
        de[k++]=nums[0];
        for(int i=1;i<nums.length;i++){
            if(nums[i]!=nums[i-1]){
                de[k++]=nums[i];
                flag=1;
            }
            else if(nums[i]==nums[i-1]&&flag<2){
                de[k++]=nums[i];
                flag++;
            }
        }
        for(int i=0;i<k;i++){
            nums[i]=de[i];
        }
        return k;

    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        removeDuplicates(nums);
    }



}
