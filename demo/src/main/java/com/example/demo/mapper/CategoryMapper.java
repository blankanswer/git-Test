package com.example.demo.mapper;

import com.example.demo.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2022-07-06 15:50:30
* @Entity com.example.demo.entity.Category
*/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}




