package com.yangmao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yangmao.domain.Menu;
import com.yangmao.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    List<String> selectPermsById(Long userId);
}
