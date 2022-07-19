package com.example.demo.mapper;

import com.example.demo.entity.SetmealDish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【setmeal_dish(套餐菜品关系)】的数据库操作Mapper
* @createDate 2022-07-10 15:57:22
* @Entity com.example.demo.entity.SetmealDish
*/
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

}




