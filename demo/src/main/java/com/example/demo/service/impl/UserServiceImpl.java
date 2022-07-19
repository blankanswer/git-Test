package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2022-07-12 11:58:57
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




