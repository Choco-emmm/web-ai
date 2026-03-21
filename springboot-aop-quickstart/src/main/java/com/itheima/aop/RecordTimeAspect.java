package com.itheima.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect//标识当前不是普通类，是AOP类
@Component
public class RecordTimeAspect {

    @Around("execution(* com.itheima.service.impl.*.*(..))")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
       //1.记录方法运行开始时间
        long begin = System.currentTimeMillis();
        //2.执行原始方法
        Object result = pjp.proceed();
        //3.记录方法运行结束时间
        long end = System.currentTimeMillis();
        log.info("方法{}运行耗时：{}ms",pjp.getSignature(),end-begin);
        return result;
    }
}
