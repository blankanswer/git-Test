package com.example.demo.mapper;

import com.example.demo.entity.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-07-17 17:21:11
* @Entity com.example.demo.entity.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




