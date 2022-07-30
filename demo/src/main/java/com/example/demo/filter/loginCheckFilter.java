package com.example.demo.filter;

import ch.qos.logback.classic.sift.JNDIBasedContextDiscriminator;
import com.alibaba.fastjson.JSON;
import com.example.demo.common.R;
import com.example.demo.common.baseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//完善登录功能
@Slf4j
@WebFilter(filterName = "loginCheckFilter" ,urlPatterns = "/*")
@Component//加这个 在启动类就不用上@ServletComponentScan
public class loginCheckFilter implements Filter {
    //路径匹配器
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        //获取请求的url
        String requestURI = request.getRequestURI();
        String []urls =new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        //判断本次请求的url是否需要被处理
        boolean check=check(requestURI,urls);
        if(check){
            //不需要 就放行
//            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);//放行
            return;
        }
            //判断你登陆状态 如果已经登录 就放行
         if(request.getSession().getAttribute("employee")!=null){
//             log.info("用户已经登录,用户id为：{}",request.getSession().getAttribute("employee"));
             Long  empId = (Long) request.getSession().getAttribute("employee");
             baseContext.setCurrentId(empId);
             filterChain.doFilter(request,response);//放行
             return;
         }

        if(request.getSession().getAttribute("user")!=null){
            Long  userId = (Long) request.getSession().getAttribute("user");
            baseContext.setCurrentId(userId);
            filterChain.doFilter(request,response);//放行
            return;
        }
        //如果未登录 则返回未登录结果
            log.info("用户未登录");
            response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }
    /**
     * 路径匹配
     * @param requestURI
     * @param URLs
     * @return
     */
    public boolean check(String requestURI,String[]URLs){
        for (String url : URLs) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match)
                return true;
        }
        return false;
    }
}
