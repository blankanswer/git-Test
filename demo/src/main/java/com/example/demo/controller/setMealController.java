package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.*;
import com.example.demo.service.CategoryService;
import com.example.demo.service.SetmealDishService;
import com.example.demo.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/setmeal")
public class setMealController {
    @Autowired
    SetmealService setmealService;

    @Autowired
    SetmealDishService setmealDishService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/page")
    public R<Page> getPageByid(int page,int pageSize,String name){
        Page<Setmeal> pageInfo=new Page<>(page,pageSize);

        Page<SetmealDto> setmealDtoPage=new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();

        if(name!=null)
            queryWrapper.like(Setmeal::getName,name);
        queryWrapper.orderByAsc(Setmeal::getUpdateTime);
        setmealService.page(pageInfo,queryWrapper);
        //拷贝第一次 不要考records数据
        BeanUtils.copyProperties(pageInfo,setmealDtoPage,"records");

        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list =records.stream().map((item)->{
            SetmealDto setmealDto = new SetmealDto();

            //这次再考records，并且要存在dishDto中
            BeanUtils.copyProperties(item,setmealDto);

            Long categoryId = item.getCategoryId();


            Category category = categoryService.getById(categoryId);
            //一定要注意这个category 的空指针异常 因为有的记录里面没有category 该字段不为空的才赋值
            if(category!=null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }

            return setmealDto;

        }).collect(Collectors.toList());

        setmealDtoPage.setRecords(list);
        return R.sucess(setmealDtoPage);

    }

    @GetMapping("/dish/{id}")
    public R<SetmealDto> frontGetById(@PathVariable Long id){
        return R.sucess(setmealService.GetSetmealByIdWithDish(id));
    }

    @CacheEvict(value = "setMealCache",allEntries = true)
    @PostMapping()
    public R<String> save(@RequestBody SetmealDto setmealDto){
        setmealService.saveWithDish(setmealDto);
        return R.sucess("新增套餐成功") ;

    }

    @GetMapping("/{id}")
    public R<SetmealDto> getById(@PathVariable Long id) {

       return R.sucess(setmealService.GetSetmealByIdWithDish(id));

    }


    @Cacheable(value = "setMealCache",key = "#setmeal.categoryId+'_'+#setmeal.status")
    @GetMapping("/list")
    public R<List<SetmealDto>> list(Setmeal setmeal){
        LambdaQueryWrapper<Setmeal> queryWrapper=new LambdaQueryWrapper<>();

       queryWrapper.eq(Setmeal::getStatus,1);

       queryWrapper.eq(Setmeal::getCategoryId, setmeal.getCategoryId());

        List<Setmeal> setmealList = setmealService.list(queryWrapper);

        List<SetmealDto> setmealDtoList = setmealList.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
            Long categoryId = item.getCategoryId();
            Category category = categoryService.getById(categoryId);
            //一定要注意这个category 的空指针异常 因为有的记录里面没有category 该字段不为空的才赋值
            if(category!=null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            Long setmealId = item.getId();
            LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(SetmealDish::getSetmealId,setmealId);
            List<SetmealDish> setmealDishList = setmealDishService.list(lambdaQueryWrapper);
            setmealDto.setSetmealDishes(setmealDishList);

            return setmealDto;

        }).collect(Collectors.toList());

        return R.sucess(setmealDtoList);
    }


    @PutMapping
    public R<String>  updateWithDish(@RequestBody SetmealDto setmealDto){
        setmealService.updageWithDish(setmealDto);
        return R.sucess("修改菜品成功");
    }

    @PostMapping("/status/{params.status}")
    public R<String> updateStatus(String ids){
        String[] idArray = ids.split(",");

        for (String id :
                idArray) {
            Setmeal setmeal = setmealService.getById(id);
            if(setmeal.getStatus()==1)
                setmeal.setStatus(0);
            else
                setmeal.setStatus(1);
            setmealService.updateById(setmeal);
        }
        return R.sucess("修改状态成功");
    }

    @DeleteMapping
    @CacheEvict(value = "setMealCache",allEntries = true)
    public R<String> DeleteById(String ids){
        String[] split = ids.split(",");

        LambdaQueryWrapper<SetmealDish> queryWrapper=new LambdaQueryWrapper<>();

        for (String id:split
             ) {
            queryWrapper.eq(SetmealDish::getSetmealId,id);

            setmealDishService.remove(queryWrapper);
            setmealService.removeById(id);

        }
        return R.sucess("删除成功");
    }
}
