package com.itheima.Interceptor;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        //1.获取请求路径
//        String path = request.getRequestURI();
//        //2.判断是否是登录请求，如果路径包含/login，说明是登录操作，放行
//        if (path.contains("/login")) {
//            log.info("登录操作");
//            return  true;
//        }
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，若不存在说明用户没有登录，返回401
        if (token == null) {
            log.info("未登录");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        //5.如果令牌存在，校验令牌，如果校验失败，也返回401
        //只要无法解析就是非法令牌，直接处理错误就行
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer id = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(id);
            log.info("当前用户id为：{}",id);
        } catch (Exception e) {
            log.info("令牌校验失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return  false;
        }
        //6.校验通过，放行
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        CurrentHolder.remove();
        log.info("清理当前用户id");
    }
}
