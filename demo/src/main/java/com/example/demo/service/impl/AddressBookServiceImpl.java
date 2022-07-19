package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.AddressBook;
import com.example.demo.service.AddressBookService;
import com.example.demo.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author 24528
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2022-07-16 17:09:07
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




