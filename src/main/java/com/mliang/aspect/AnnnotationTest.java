package com.mliang.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    //@Pointcut("execution(* com.mliang.service.impl..*.*(..))")
    @Pointcut("execution(* com.mliang.controller.login..*.*(..))")
    private void controllerAspect(){}//定义一个切入点


    @Before("controllerAspect()")
    public void paramValid(JoinPoint point) {
        System.out.println("我是前置通知.....");
    }
    @After("controllerAspect()")
    public void after(JoinPoint point) {
        System.out.println("我是后置通知.....");
        //省略
    }
    @Around("controllerAspect()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable{
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session= request.getSession();
        System.out.println("我是环绕通知.....");
        //手动执行目标方法
        if(session.getAttribute("user") == null){
            JSONObject json = new JSONObject();
            json.put("code","100");
            json.put("msg","用户未登录");
            return json.toJSONString();
        }else{
            Object obj = joinPoint.proceed();
            return obj;
        }

    }

}
