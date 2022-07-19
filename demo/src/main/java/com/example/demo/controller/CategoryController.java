package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    //分页查询
    @GetMapping("/page")
    public R<Page> getCategoryById(int page,int pageSize,String name){
        Page pageInfo =new Page(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();

        if(name!=null)
            queryWrapper.like(Category::getName,name);

        queryWrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo,queryWrapper);
        return  R.sucess(pageInfo);

    }

    //新增菜品
    @PostMapping()
    public R<Category> insertCategory(@RequestBody Category category){
        log.info("category:{}",category);
        if(categoryService.save(category))
            return R.sucess(category);
        return R.error("添加失败");
    }

    @DeleteMapping
    public R<String> deleteCategoryById(Long ids){
        log.info("删除id：{}",ids);
        if(categoryService.remove(ids))
            return R.sucess("分类信息删除成功");
        return R.error("删除失败");
    }


    @PutMapping()
    public R<String> updateCategory(@RequestBody Category category){
        log.info("修改分类信息：{}",category);
        categoryService.updateById(category);
        return R.sucess("修改信息成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        LambdaQueryWrapper<Category> lambdaQueryWrapper=new LambdaQueryWrapper();

        lambdaQueryWrapper.eq( category.getType()!=null,Category::getType,category.getType());

        lambdaQueryWrapper.orderByAsc(Category::getSort);

        lambdaQueryWrapper.orderByDesc(Category::getUpdateTime);

        List<Category> categories = categoryService.list(lambdaQueryWrapper);

        return R.sucess(categories);

    }
}
