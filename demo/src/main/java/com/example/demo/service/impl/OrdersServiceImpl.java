package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.baseContext;
import com.example.demo.common.customException;
import com.example.demo.entity.*;
import com.example.demo.service.*;
import com.example.demo.mapper.OrdersMapper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.util.deparser.ShowStatementDeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author 24528
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2022-07-18 00:44:56
*/
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    UserService userService;

    @Autowired
    AddressBookService addressBookService;

    @Autowired
    OrderDetailService orderDetailService;
    /**
     * 用户下单
     * @param orders
     */
    @Transactional
    @Override
    public void submit(Orders orders) {
        //获得当前用户的id
        Long userId = baseContext.getCurrentId();

        //查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(lambdaQueryWrapper);
        if(shoppingCartList==null||shoppingCartList.size()==0){
            throw new customException("购物车为空");
        }

        User user = userService.getById(userId);

        Long addressBookId = orders.getAddressBookId();

        long orderId = IdWorker.getId();

        AddressBook addressBook = addressBookService.getById(addressBookId);
        //原子操作，保证线程安全
        AtomicInteger amount=new AtomicInteger(0);

        List<OrderDetail> orderDetails = shoppingCartList.stream().map((shoppingCart) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(shoppingCart.getNumber());
            orderDetail.setDishFlavor(shoppingCart.getDishFlavor());
            orderDetail.setSetmealId(shoppingCart.getSetmealId());
            orderDetail.setName(shoppingCart.getName());
            orderDetail.setImage(shoppingCart.getImage());
            orderDetail.setAmount(shoppingCart.getAmount());
            amount.addAndGet(shoppingCart.getAmount().multiply(new BigDecimal(shoppingCart.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        //向订单表插入数据



        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));//amount计算的总金额
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress(addressBook.getProvinceName()==null?"":addressBook.getProvinceName()+
                (addressBook.getCityName()==null?"":addressBook.getCityName())+
                (addressBook.getDistrictName()==null?"":addressBook.getDistrictName())+
                (addressBook.getDetail()==null?"":addressBook.getDetail()));

        this.save(orders);
        //向订单明细表插入数据（可能是多条数据）
        orderDetailService.saveBatch(orderDetails);
        //清空购物车数据

        shoppingCartService.remove(lambdaQueryWrapper);
    }
}




