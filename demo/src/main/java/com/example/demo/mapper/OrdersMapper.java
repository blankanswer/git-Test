package com.example.demo.mapper;

import com.example.demo.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【orders(订单表)】的数据库操作Mapper
* @createDate 2022-07-18 00:44:56
* @Entity com.example.demo.entity.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




