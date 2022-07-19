package com.example.demo.service;

import com.example.demo.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 24528
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2022-07-18 00:44:56
*/
public interface OrdersService extends IService<Orders> {
        public void submit(Orders orders);
}
