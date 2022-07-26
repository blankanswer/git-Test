package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.Category;
import com.example.demo.entity.Dish;
import com.example.demo.entity.DishDto;
import com.example.demo.entity.DishFlavor;
import com.example.demo.service.CategoryService;
import com.example.demo.service.DishFlavorService;
import com.example.demo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping()//这里要加@requestBody，因为是json格式
    public R<String> save(@RequestBody DishDto dishDto){

        dishService.saveWithFlavor(dishDto);


//        清理所有的key
//        Set keys = redisTemplate.keys("dish_*");
//
//        redisTemplate.delete(keys);

        //单独清理某个key（某个分类）
        String key="dish_"+dishDto.getCategoryId()+"_1";
        redisTemplate.delete(key);
        return R.sucess("新增菜品成功");

    }

    @GetMapping("/page")
    public R<Page> getMealById(int page,int pageSize, String name) {
        Page<Dish> pageInfo=new Page<>(page,pageSize);
        Page<DishDto> dishDtoPage=new Page<>();

        LambdaQueryWrapper<Dish> queryWrapper=new LambdaQueryWrapper<>();

        if(name!=null)
            queryWrapper.like(Dish::getName,name);
        queryWrapper.orderByAsc(Dish::getSort);
        dishService.page(pageInfo,queryWrapper);
        //拷贝第一次 不要考records数据
        BeanUtils.copyProperties(pageInfo,dishDtoPage,"records");

        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list =records.stream().map((item)->{
            DishDto dishDto = new DishDto();

            //这次再考records，并且要存在dishDto中
            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getCategoryId();


            Category category = categoryService.getById(categoryId);
            //一定要注意这个category 的空指针异常 因为有的记录里面没有category 该字段不为空的才赋值
            if(category!=null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }

            return dishDto;

        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);
        return R.sucess(dishDtoPage);

    }

    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable Long id  ){

        DishDto dishDto = dishService.getByIdWithFlavor(id);

        return R.sucess(dishDto);
    }

    @PutMapping()//这里要加@requestBody，因为是json格式
        public R<String> update(@RequestBody DishDto dishDto){

        dishService.UpdateWithFlavor(dishDto);

//        清理所有的key
//        Set keys = redisTemplate.keys("dish_*");
//
//        redisTemplate.delete(keys);

        //单独清理某个key（某个分类）
        String key="dish_"+dishDto.getCategoryId()+"_1";
        redisTemplate.delete(key);



        return R.sucess("修改菜品成功");

    }

    @PostMapping("/status/{param.status}")
        public R<String> updateStatus(String ids){

        String[] split = ids.split(",");
        for (String id:
            split) {
            Dish dishServiceById = dishService.getById(id);
            if(dishServiceById.getStatus()==1)
                dishServiceById.setStatus(0);
            else
                dishServiceById.setStatus(1);
            dishService.updateById(dishServiceById);
        }

        return R.sucess("修改售餐状态成功") ;
    }


    @DeleteMapping()
        public R<String> deleteById(String ids){
        String[] split = ids.split(",");
        for (String id:
             split ) {
            Dish dish = dishService.getById(id);
            dishFlavorService.removeById(id);
            dishService.removeById(dish);

        }

        return R.sucess("删除菜品成功");
    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish){



        List<DishDto> dishDtoList =null;

        Category category1=null;

        String key= "dish_"+dish.getCategoryId()+"_"+dish.getStatus();

        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);

        if(dishDtoList!=null){
            log.info("查询缓存");
            return R.sucess(dishDtoList);
        }


        //先要查dish表 把起售的查出来
        //select* from dish where category_Id=? ,status=1 orderBy...
        LambdaQueryWrapper<Dish> lambdaQueryWrapper=new LambdaQueryWrapper();

        lambdaQueryWrapper.eq( dish.getCategoryId()!=null,Dish::getCategoryId,dish.getCategoryId());

        lambdaQueryWrapper.eq(Dish::getStatus,1);

        lambdaQueryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);

        List<Dish> dishes = dishService.list(lambdaQueryWrapper);

        dishDtoList =dishes.stream().map((item)->{
            DishDto dishDto = new DishDto();

            //这次把每个item拷贝，并且要存在dishDto中
            BeanUtils.copyProperties(item,dishDto);

            Long categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);
            //一定要注意这个category 的空指针异常 因为有的记录里面没有category 该字段不为空的才赋值
            if(category!=null){
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            Long dishId = item.getId();

            //select * from dish_flavor where dish_Id= ?
            LambdaQueryWrapper<DishFlavor>  queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(DishFlavor::getDishId,dishId);
           //得到口味表的集合
            List<DishFlavor> dishFlavorList = dishFlavorService.list(queryWrapper);

            //再set进dishDto里面
            dishDto.setFlavors(dishFlavorList);

            return dishDto;

        }).collect(Collectors.toList());

        redisTemplate.opsForValue().set(key,dishDtoList,60, TimeUnit.MINUTES);
        return R.sucess(dishDtoList);

    }

}
