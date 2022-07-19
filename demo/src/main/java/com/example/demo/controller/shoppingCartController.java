package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.R;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.common.baseContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class shoppingCartController {
    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart) {

        log.info("购物车数据：{}", shoppingCart);

        //设置用户id，指定当前是那个用户的购物车数据
        Long currentId = baseContext.getCurrentId();
        shoppingCart.setUserId(currentId);

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, currentId);

        Long dishId = shoppingCart.getDishId();
        //查询当前菜品或者是套餐是否在购物车当中
        if (dishId != null) {
            //添加的是菜品id
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
//            前端不支持口味分开，添加第二份的时候，不允许选口味，我真的要郁郁辣
//            queryWrapper.eq(ShoppingCart::getDishFlavor,shoppingCart.getDishFlavor());
        } else {
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        //如果已经存在（口味这些都一样），就在原来的基础上加一
        if (cartServiceOne != null) {
            Integer number = cartServiceOne.getNumber();
            cartServiceOne.setNumber(number + 1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.updateById(cartServiceOne);
        } else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }
        //否则在表中加一条数据，数量默认是1
        return R.sucess(cartServiceOne);
    }

    /**
     * 查看购物车
     *
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list() {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, baseContext.getCurrentId());
        lambdaQueryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(lambdaQueryWrapper);

        return R.sucess(list);
    }

    @DeleteMapping("/clean")
    public R<String> delete() {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, baseContext.getCurrentId());
        shoppingCartService.remove(lambdaQueryWrapper);

        return R.sucess("清除购物车成功");
    }

    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, baseContext.getCurrentId());
        Long dishId = shoppingCart.getDishId();
        if (dishId != null) {
            lambdaQueryWrapper.eq(ShoppingCart::getDishId, dishId);
            ShoppingCart cartServiceOne = shoppingCartService.getOne(lambdaQueryWrapper);
            cartServiceOne.setNumber(cartServiceOne.getNumber() - 1);
            if (cartServiceOne.getNumber() != 0) {
                shoppingCartService.updateById(cartServiceOne);
                return R.sucess(cartServiceOne);
            }
            shoppingCartService.removeById(cartServiceOne);
            return R.sucess(cartServiceOne);
        } else {
            Long setmealId = shoppingCart.getSetmealId();
            lambdaQueryWrapper.eq(ShoppingCart::getSetmealId, setmealId);
            ShoppingCart cartServiceOne = shoppingCartService.getOne(lambdaQueryWrapper);
            cartServiceOne.setNumber(cartServiceOne.getNumber() - 1);
            if (cartServiceOne.getNumber() != 0) {
                shoppingCartService.updateById(cartServiceOne);
                return R.sucess(cartServiceOne);
            }

            shoppingCartService.removeById(cartServiceOne);
            return R.sucess(cartServiceOne);
        }

    }
}