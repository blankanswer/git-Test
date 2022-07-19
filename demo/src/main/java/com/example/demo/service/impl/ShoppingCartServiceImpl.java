package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2022-07-17 17:21:11
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




