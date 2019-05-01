package com.itcast.ssm.controller;

import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Aop切面类
 */
@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private SysLogService sysLogService;

    private Date visitTime;//访问的开始时间
    private Class clazz;//执行的类
    private Method method;//执行的方法

    /**
     * 切点表达式
     */
    @Pointcut("execution(* com.itcast.ssm.controller.*.*(..))")
    public void p() {
    }

    /**
     * 前置通知 获取访问的开始时间、执行的类和执行的方法
     *
     * @param jp
     */
    @Before("p()")
    public void doBefore(JoinPoint jp) throws Exception {
        visitTime = new Date();//1.获取访问开始时间
        clazz = jp.getTarget().getClass();//2.获取当前访问的具体类
        //3.获取访问时具体执行的方法的Method对象
        String methodName = jp.getSignature().getName();//当前访问具体执行的方法的名字
        Object[] args = jp.getArgs();//获取访问执行方法的参数
        if (args == null || args.length == 0) {
                method =clazz.getMethod(methodName);//只能获取无参数方法的对象
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            method =clazz.getMethod(methodName, classArgs);//获取有参数方法的对象
        }
    }

    /**
     * 最终通知
     *
     * @param jp
     */
    @After("p()")
    public void doAfter(JoinPoint jp) throws Exception {
        long burnTime = new Date().getTime() - visitTime.getTime();//1.获取访问时长
        String url = "";//2.访问的url
        //获取访问的url
        if (method != null && clazz != null && clazz != LogAop.class&&clazz != SysLogController.class) {
            //2.1获取类上的@RequestMapping("/xxx")
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if (classAnnotation != null) {
                String[] classValue = classAnnotation.value();
                //2.2获取方法上的@RequestMapping("/xxx")
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if (methodAnnotation != null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];

                    //3.获取访问的ip地址
                    String ip = request.getRemoteAddr();
                    //4.获取当前操作的用户
                    // 可以通过securityContext获取，也可以从request.getSession中获取
                    SecurityContext context = SecurityContextHolder.getContext();
                    User user = (User) context.getAuthentication().getPrincipal();//从上下文获取登录的用户
                    String username = user.getUsername();//获取到用户名

                    //5,将日志信息封装到SysLog对象中
                    SysLog sysLog = new SysLog();
                    //5.1设置访问时间
                    sysLog.setVisitTime(visitTime);
                    //5.2设置执行方法
                    sysLog.setMethod("类名：" + clazz.getName() + " 方法名：" + method.getName());
                    //5.3设置执行时长
                    sysLog.setExecutionTime(burnTime);
                    //5.4设置url
                    sysLog.setUrl(url);
                    //5.5设置ip
                    sysLog.setIp(ip);
                    //5.6设置操作用户
                    sysLog.setUsername(username);

                    //6.调用Service中的save访问，完成日志的添加
                    sysLogService.save(sysLog);
                }
            }
        }
    }
}
