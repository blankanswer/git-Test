package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【user(用户信息)】的数据库操作Mapper
* @createDate 2022-07-12 11:58:57
* @Entity com.example.demo.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




