package com.example.demo.mapper;

import com.example.demo.entity.AddressBook;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【address_book(地址管理)】的数据库操作Mapper
* @createDate 2022-07-16 17:09:07
* @Entity com.example.demo.entity.AddressBook
*/
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}




