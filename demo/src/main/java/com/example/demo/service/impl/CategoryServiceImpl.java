package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.customException;
import com.example.demo.entity.Category;
import com.example.demo.entity.Dish;
import com.example.demo.entity.Setmeal;
import com.example.demo.mapper.SetmealMapper;
import com.example.demo.service.CategoryService;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.service.DishService;
import com.example.demo.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2022-07-06 15:50:30
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public boolean remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper=new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int count1 = dishService.count(dishLambdaQueryWrapper);
        if(count1>0){
            throw new customException("当前分类关联了菜品，不能删除");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper=new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int count2=setmealService.count(setmealLambdaQueryWrapper);
        if(count2>0){
            throw new customException("当前分类关联了套餐，不能删除");

        }
            super.removeById(id);//这个还是调用mp里的方法
            return true;
    }
}




