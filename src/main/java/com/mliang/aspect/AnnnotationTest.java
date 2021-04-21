package com.mliang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AnnnotationTest {

    //配置接入点
    @Pointcut("execution(* com.mliang.controller..*.*(..))")
    private void controllerAspect(){}//定义一个切入点


    @Before("controllerAspect()")
    public void paramValid(JoinPoint point) {
        System.out.println("我第一个执行.....");
        //省略
    }
    @After("controllerAspect()")
    public void after(JoinPoint point) {
        System.out.println("我第三个执行.....");
        //省略
    }
}
