package com.vasylchenko.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterThrowing(pointcut = "execution (public * *(..))",throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Exception e) {
        LOGGER.error(joinPoint.getSignature().toLongString() + " exception here!");
    }
}