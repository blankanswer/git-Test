package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.SetmealDish;
import com.example.demo.entity.SetmealDto;
import com.example.demo.service.SetmealDishService;
import com.example.demo.mapper.SetmealDishMapper;
import com.example.demo.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【setmeal_dish(套餐菜品关系)】的数据库操作Service实现
* @createDate 2022-07-10 15:57:22
*/
@Service
public class SetmealDishServiceImpl extends ServiceImpl<SetmealDishMapper, SetmealDish>
    implements SetmealDishService{

}




