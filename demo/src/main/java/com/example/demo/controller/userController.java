package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.demo.common.R;
import com.example.demo.common.baseContext;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.SMSUtils;
import com.example.demo.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/user")
public class userController {
    @Autowired
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        //获取手机好
        String phone = user.getPhone();

        if(StringUtils.isNotEmpty(phone)){
            //生成4位数验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info("code:{}",code);
            //利用阿里云提供的短信服务api发短信
            SMSUtils.sendMessage("阿里云短信测试","SMS_154950909",phone,code);//这里我没有审核通过的签名和模板就算了
            //需要将生成的验证码保存到session
//            session.setAttribute(phone,code);//key:phone value:code
            //存在redis当中
            stringRedisTemplate.opsForValue().set(phone,code, 10L, TimeUnit.MINUTES);
            return R.sucess("发送请求成功");
        }
        return R.error("短信发送失败");
    }


    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session, HttpServletRequest httpServletRequest){
        String phone = map.get("phone").toString();
        String code = map.get("code").toString();
//        String code="1234";


        Object codeSession = stringRedisTemplate.opsForValue().get(phone);

        if(codeSession!=null&&codeSession.equals(code)){
            LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(queryWrapper);
            if(user==null){
                user=new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            //这里很重要！！！ 要把user保存到session里面 之后过滤器才能查到user
            session.setAttribute("user",user.getId());

            //这里用户登录以后，再删除验证码
            stringRedisTemplate.delete(phone);

            return R.sucess(user);
        }
        return R.error("验证码错误");
    }
//    @PostMapping("/login")
//    public R<String> login()

}
