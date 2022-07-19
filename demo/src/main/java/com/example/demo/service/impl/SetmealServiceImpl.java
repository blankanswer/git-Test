package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.Setmeal;
import com.example.demo.entity.SetmealDish;
import com.example.demo.entity.SetmealDto;
import com.example.demo.service.SetmealDishService;
import com.example.demo.service.SetmealService;
import com.example.demo.mapper.SetmealMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author 24528
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2022-07-08 09:41:32
*/
@Slf4j
@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{

    @Autowired
    SetmealDishService setmealDishService;

    @Override
    @Transactional
    public void saveWithDish(SetmealDto setmealDto) {
        log.info("套餐信息：{}",setmealDto);
        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
//        String dtoId = String.valueOf(setmealDto.getId());
//
//       setmealDishes.stream().map((item)->{
//           item.setSetmealId(setmealDto.getId());
//           return item;
//       }).collect(Collectors.toList())  ;
        for (SetmealDish s:
             setmealDishes ) {
            s.setSetmealId(setmealDto.getId());
        }

        setmealDishService.saveBatch(setmealDishes);

    }

    @Override
    @Transactional
    public void updageWithDish(SetmealDto setmealDto) {
        //基本信息修改
        this.updateById(setmealDto);
        //清理菜品集合
        LambdaQueryWrapper<SetmealDish>queryWrapper=new LambdaQueryWrapper<>();

        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId()    );

        setmealDishService.remove(queryWrapper);
        //重新插入菜品集合到数据库
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish s:setmealDishes
             ) {s.setSetmealId(setmealDto.getId());

        }
        //批量保存
        setmealDishService.saveBatch(setmealDishes);


    }

    @Override
    public SetmealDto GetSetmealByIdWithDish(Long id) {
        //先拿到套餐基本参数
        Setmeal setmeal = this.getById(id);

        SetmealDto setmealDto = new SetmealDto();

        BeanUtils.copyProperties(setmeal,setmealDto);
        //查套餐的菜品 要用套餐菜品表来查
        LambdaQueryWrapper<SetmealDish> queryWrapper=new LambdaQueryWrapper<>();
        //这里setmealId实际上就是当前setmealDto的id 因为上面save方法里就是这么赋值的
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDto.getId());
       //得到菜品的集合
        List<SetmealDish> setmealDishes = setmealDishService.list(queryWrapper);
        //赋值给当前dto
        setmealDto.setSetmealDishes(setmealDishes);

        return setmealDto;
    }
}




