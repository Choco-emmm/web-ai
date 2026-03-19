package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class TokenFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //本来就是这个类型，强转就行

        //1.获取请求路径
        String path = request.getRequestURI();
        //2.判断是否是登录请求，如果路径包含/login，说明是登录操作，放行
        if (path.contains("/login")) {
            log.info("登录操作");
            filterChain.doFilter(request, response);
            return;
        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，若不存在说明用户没有登录，返回401
        if (token == null) {
            log.info("未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //5.如果令牌存在，校验令牌，如果校验失败，也返回401
        //只要无法解析就是非法令牌，直接处理错误就行
        try {
            JwtUtils.parseToken(token);
        } catch (Exception e) {
            log.info("令牌校验失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        //6.校验通过，放行
        filterChain.doFilter(request, response);
    }


}
