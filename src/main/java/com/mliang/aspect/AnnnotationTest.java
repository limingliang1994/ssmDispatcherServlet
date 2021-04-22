package com.mliang.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class AnnnotationTest {
//    .execution()  用于描述方法 【掌握】
//    语法：execution(修饰符  返回值  包.类.方法名(参数) throws异常)
//    修饰符，一般省略
//    public		公共方法
//			*			任意
//    返回值，不能省略
//    void			返回没有值
//    String		返回值字符串
//			* 			任意
//    包，[省略]
//    com.itheima.crm			固定包
//    com.itheima.crm.*.service	crm包下面子包任意 （例如：com.itheima.crm.staff.service）
//    com.itheima.crm..			crm包下面的所有子包（含自己）
//    com.itheima.crm.*.service..	crm包下面任意子包，固定目录service，service目录任意包
//    类，[省略]
//    UserServiceImpl			指定类
//			*Impl					以Impl结尾
//    User*					以User开头
//			*						任意
//    方法名，不能省略
//    addUser					固定方法
//    add*						以add开头
//			*Do						以Do结尾
//			*						任意
//            (参数)
//			()						无参
//            (int)						一个整型
//			(int ,int)					两个
//            (..)						参数任意
    //配置接入点
    @Pointcut("execution(* com.mliang.service.impl..*.*(..))")
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
    @Around("controllerAspect()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("前");
        //手动执行目标方法
        Object obj = joinPoint.proceed();

        System.out.println("后");
        return obj;
    }

}
