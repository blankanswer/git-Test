package com.example.demo.mapper;

import com.example.demo.entity.DishFlavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Mapper
* @createDate 2022-07-08 15:35:19
* @Entity com.example.demo.entity.DishFlavor
*/
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

}




