package com.example.demo.service;

import com.example.demo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 24528
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service
* @createDate 2022-07-06 15:50:30
*/
public interface CategoryService extends IService<Category> {
    public boolean remove(Long id);
}
