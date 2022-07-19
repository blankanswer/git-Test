package com.example.demo.service;

import com.example.demo.common.R;
import com.example.demo.entity.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.DishDto;

/**
* @author 24528
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2022-07-08 09:38:52
*/
public interface DishService extends IService<Dish> {
    //新增菜品 需要操作两张表来来取得数据

    public void saveWithFlavor(DishDto dishDto);


    //修改菜品

    public DishDto getByIdWithFlavor(Long id);


    void UpdateWithFlavor(DishDto dishDto);
}
