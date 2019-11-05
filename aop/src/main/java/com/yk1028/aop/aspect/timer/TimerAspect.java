package com.yk1028.aop.aspect.timer;

import com.yk1028.aop.aspect.logging.LoggingAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimerAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(com.yk1028.aop.aspect.timer.Timer)")
    public Object doBasicProfiling(ProceedingJoinPoint proceedingJoinPoint) {
        Object result;
        try {
            long start = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long processingTime = System.currentTimeMillis() - start;

            log.info("수행 시간 : {}", processingTime);
        } catch (Throwable throwable) {
            log.info("Timer Aspect Exception : {}", throwable);
            throw new RuntimeException(throwable);
        }
        return result;
    }
}
