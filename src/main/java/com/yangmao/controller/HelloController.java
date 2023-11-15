package com.yangmao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('system:dept:test')")
    @PreAuthorize("@ex.hasAuthority('system:dept:test')")
    public String hello(){
       return "hello!";
    }

}
