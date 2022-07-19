package com.example.demo.controller;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.example.demo.common.baseContext;
import com.example.demo.common.R;
import com.example.demo.entity.AddressBook;
import com.example.demo.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.QuitResponse;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/addressBook")
public class addressBookController {

    @Autowired
    private AddressBookService addressBookService;

    @PutMapping
    public R<AddressBook> update(@RequestBody AddressBook addressBook){
        addressBook.setUserId(baseContext.getCurrentId());
//        addressBook.setCreateTime(LocalDateTime.now());
//        addressBook.setCreateUser(baseContext.getCurrentId());
        log.info("address:{}",addressBook);
        addressBookService.updateById(addressBook);
        return R.sucess(addressBook);
    }

    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(baseContext.getCurrentId());
        addressBook.setCreateTime(LocalDateTime.now());
        addressBook.setCreateUser(baseContext.getCurrentId());
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBook.setUpdateUser(baseContext.getCurrentId());
        log.info("address:{}",addressBook);
        addressBookService.save(addressBook);
        return R.sucess(addressBook);
    }


    @Transactional
    @PutMapping("/default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        LambdaUpdateWrapper <AddressBook> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(AddressBook::getUserId,baseContext.getCurrentId());
        updateWrapper.set(AddressBook::getIsDefault,0);
        addressBookService.update(updateWrapper );

        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return R.sucess(addressBook);
    }

    @GetMapping("/{id}")
    public R<AddressBook> get(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if(addressBook!=null)
            return R.sucess(addressBook);
        return R.error("没有找到对象");

    }

    @GetMapping("/default")
    public R<AddressBook> getDefault(){
        LambdaQueryWrapper<AddressBook>queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,baseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault,1);
        AddressBook defaultAddress = addressBookService.getOne(queryWrapper);
        if(defaultAddress!=null)
            return R.sucess(defaultAddress);
        return R.error("没有找到默认地址");
    }

    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook){
        addressBook.setUserId(baseContext.getCurrentId());

        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(addressBook.getId()!=null,AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        return R.sucess(addressBookService.list(queryWrapper));
    }


    @DeleteMapping
    public R<String> delete(Long ids){
        addressBookService.removeById(ids);
        return R.sucess("删除成功");
    }

}
