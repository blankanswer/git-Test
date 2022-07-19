package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.OrderDetailService;
import com.example.demo.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-07-18 00:48:18
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




