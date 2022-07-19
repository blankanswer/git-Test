package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.R;
import com.example.demo.entity.Dish;
import com.example.demo.entity.DishDto;
import com.example.demo.entity.DishFlavor;
import com.example.demo.service.DishFlavorService;
import com.example.demo.service.DishService;
import com.example.demo.mapper.DishMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.spi.DirStateFactory;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 24528
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2022-07-08 09:38:52
*/
@Service

public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    @Autowired
    private DishFlavorService dishFlavorService;



    /**
     *
     *
     */
    @Override
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long dtoId = dishDto.getId();

        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors.stream().map((item)->{
            item.setDishId(dtoId);
            return item;
        }).collect(Collectors.toList());


        dishFlavorService.saveBatch(dishDto.getFlavors());
    }



    @Override
    public DishDto getByIdWithFlavor(Long id) {
        //获取当前dish的属性并保存到dish
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        //copy到dishDto
        BeanUtils.copyProperties(dish,dishDto);
        //通过dishId查flavor表
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dish.getId());
        //查询结果保存集合
        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);

        dishDto.setFlavors(flavors);

        return dishDto;
    }

    @Override
    @Transactional
    public void UpdateWithFlavor(DishDto dishDto) {
       //基本信息赋值
        this.updateById(dishDto);
        //清理原先的口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(DishFlavor::getDishId,dishDto.getId());

        dishFlavorService.remove(queryWrapper);
        //插入新的口味信息保存到数据库

        List<DishFlavor> flavors = dishDto.getFlavors();

        flavors.stream().map((item)->{
            item.setDishId(dishDto.getId());

            return item;
        }).collect(Collectors.toList());


        dishFlavorService.saveBatch(flavors);//批量保存
    }
}




