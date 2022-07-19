package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.R;
import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Slf4j//日志
@RestController//表明这是rest风格controller
@RequestMapping("/employee")//映射到/employee下面
public class EmployeeController {
    //自动装一下接口（比装实体类好，这样就在服务层上切换就行了，而不是装实体类，实体类改了下面全要改）
    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/login")
    public R<Employee> login(@RequestBody Employee employee, HttpServletRequest request){//需要request 的原因就是需要把employee的id传到session当中表示登录成功
        /*
          1.页面提交的密码进行MD5加密
          2、根据页面提交的username查询数据库
          3、如果没查到就返回登录失败结果
          4、密码比对 不一致则返回登陆失败结果
          5、查看员工状态 如果为已禁用 则返回员工状态已禁用的结果
          6、登录成功，将员工id存入Session并返回登录成功
         */
        // 1.页面提交的密码进行MD5加密

        String password = employee.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的username查询数据库

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);//用getOne的原因就是，user_name在数据库中有unique索引
        // 3、如果没查到就返回登录失败结果

        if(emp==null){
            return  R.error("登录失败");
        }

        if(!emp.getPassword().equals(password)){
            return R.error("登录失败");
        }

        //   5、查看员工状态 如果为已禁用 则返回员工状态已禁用的结果
        if(emp.getStatus()==0){
            return R.error("账号已经禁用");
        }

        request.getSession().setAttribute("employee",emp.getId());

        return R.sucess(emp);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        //1、清理session中的id
        request.getSession().removeAttribute("employee");

        //2、返回结果
        return R.sucess("退出成功");

    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){//requestBody注解才能正常封装json数据
        log.info("新增员工，员工信息：{}",employee.toString());
        //初始化密码 md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));//md5加密
//        //
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//        //获得对象的id
//        long empId = (long) request.getSession().getAttribute("employee");
//
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);//保存到数据库 这里的service接口是mybatis自己提供的
        return null;
    }

    /**
     * 员工信息分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> pageSelect(int page,int pageSize,String name){//page是mybatisPlus提供的分页插件
        log.info("page= {},pageSize= {},name= {} ",page,pageSize,name);
        //构造分页构造器
        Page pageInfo=new Page(page,pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper=new LambdaQueryWrapper();
        //添加一个过滤条件
        if(name!=null)
        queryWrapper.like(Employee::getName,name);
        //添加一个排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,queryWrapper);//这个分页查询功能在MP中已经写好了

        return R.sucess(pageInfo);
    }
    @PutMapping
    public R<String> update(@RequestBody Employee employee,HttpServletRequest request) {//json格式的要用@request注解
        log.info(employee.toString());//在服务端看看能不能接收
        //获得当前员工id
//        long empID = (long) request.getSession().getAttribute("employee");
//
//        employee.setUpdateTime(LocalDateTime.now());
//
//        employee.setUpdateUser(empID);
        //调用service的方法
        employeeService.updateById(employee);//这个也是mp的自己实现的方法

        return R.sucess("员工信息修改成功");
    }


    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable long id){//@pathVariable注解表明这个id是在请求路径获取到的变量
        Employee employee=employeeService.getById(id);
        if(employee!=null)
            return R.sucess(employee);

        return R.error("未找到员工信息");

    }

}
