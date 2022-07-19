package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.OrderDetail;
import com.example.demo.entity.Orders;
import com.example.demo.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/order")
@RestController
public class orderController {
    @Autowired
    OrdersService ordersService ;

    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        ordersService.submit(orders);
        return R.sucess("提交订单成功");

    }

    @GetMapping("/userPage")
    public R<Page> pageSelect(int page,int pageSize){
        Page pageInfo = new Page(page, pageSize);
        LambdaQueryWrapper<Orders> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOrderTime);
        ordersService.page(pageInfo,queryWrapper);
        return R.sucess(pageInfo);
    }
}
