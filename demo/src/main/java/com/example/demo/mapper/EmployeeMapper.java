package com.example.demo.mapper;

import com.example.demo.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24528
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2022-06-18 10:50:59
* @Entity com.example.demo.entity.Employee
*/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}




