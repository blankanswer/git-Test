package com.example.demo.service;

import com.example.demo.common.R;
import com.example.demo.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
* @author 24528
* @description 针对表【employee(员工信息)】的数据库操作Service
* @createDate 2022-06-18 10:50:59
*/
public interface EmployeeService extends IService<Employee> {

}
