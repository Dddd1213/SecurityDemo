package com.yangmao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangmao.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
