package com.vasylchenko.configs;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
    private long start = 0;

    @Pointcut("execution(* com.vasylchenko.Bootstrap.*(..))")
    public void bootstrap() {
    }

    @Pointcut("@annotation(com.vasylchenko.annotation.Loggable)")
    public void loggableMethod() {
    }

    @Before("bootstrap() && loggableMethod()")
    public void bootstrap(JoinPoint point) throws Throwable {
        start = System.currentTimeMillis();
        LOGGER.info("Method " + point.getSignature().getName() + ". Class: " + point.getSignature().getClass().getName());
    }

    @After("bootstrap() && loggableMethod()")
    public void consoleAfter(JoinPoint point) {
        LOGGER.info(
                String.format("Method %s: works %ds",
                        point.getSignature().getName(),
                        System.currentTimeMillis() - start
                ));
    }

    @Before("execution(* com.vasylchenko.Runner.*(..)) && loggableMethod()")
    public void runner(JoinPoint point){
        LOGGER.info("Run by using Runner class");
    }

    @AfterThrowing(pointcut = "execution(* *(String, ..))", throwing = "e")
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        LOGGER.error("F**k...something wrong...");
        String methodName = joinPoint.getSignature().getName();
        String stuff = joinPoint.getSignature().toString();
        String arguments = Arrays.toString(joinPoint.getArgs());
        LOGGER.error("Write something in the log... We have caught exception in method: "
                + methodName + " with arguments "
                + arguments + "\nand the full toString: " + stuff + "\nthe exception is: "
                + e.getMessage(), e);
    }
}