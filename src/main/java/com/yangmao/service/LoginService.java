package com.yangmao.service;

import com.yangmao.domain.ResponseResult;
import com.yangmao.domain.User;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
