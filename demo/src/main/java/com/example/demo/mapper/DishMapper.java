package com.example.demo.mapper;

import com.example.demo.entity.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2022-07-08 09:38:52
* @Entity com.example.demo.entity.Dish
*/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {

}




