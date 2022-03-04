package com.daryl.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author wl
 * @create 2022-02-15
 */
@Aspect
@Component
public class ServiceAspect {

/*    @Pointcut("execution(* com.daryl.service.impl.UserServiceImpl.*(..))")
    public void serviceASP(){

    }
    @Before("serviceASP()")
    public void permissionCheck(){
        System.out.println("权限校验");
    }

    @After("serviceASP()")
    public void log(){
        System.out.println("日志打印...");
    }*/
}
