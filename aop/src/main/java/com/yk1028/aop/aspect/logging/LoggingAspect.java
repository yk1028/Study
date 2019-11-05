package com.yk1028.aop.aspect.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.yk1028.aop.controller.LoggingController.hello())")
    public Object doBasicProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("method start");
        Object retVal = proceedingJoinPoint.proceed();
        log.info("method end");
        return retVal;
    }
}
