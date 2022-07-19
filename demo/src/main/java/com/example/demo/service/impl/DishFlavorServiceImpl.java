package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.DishFlavor;
import com.example.demo.service.DishFlavorService;
import com.example.demo.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2022-07-08 15:35:19
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




