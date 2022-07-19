package com.example.demo.service;

import com.example.demo.entity.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.SetmealDto;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2022-07-08 09:41:32
*/

public interface SetmealService extends IService<Setmeal> {
    public void saveWithDish(SetmealDto setmealDto);

    public void updageWithDish(SetmealDto setmealDto);

    public SetmealDto GetSetmealByIdWithDish(Long id);
}
