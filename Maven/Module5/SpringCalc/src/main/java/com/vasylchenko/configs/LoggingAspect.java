package com.vasylchenko.configs;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Before("execution(* *(..)) && @annotation(com.vasylchenko.annotation.Loggable)")
    public Object test(ProceedingJoinPoint point) throws Throwable {
        String methodName = point.getSignature().getName();
        Object result = point.proceed();
        System.out.println(methodName);
        return result;
    }
}

//    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);
//
//    @Pointcut("execution(* com.vasylchenko.Bootstrap.run())")
//    public void bootstrap() { }
//
//    @Pointcut("execution(* com.vasylchenko.beans.Console.readData(..))")
//    public void console() { }
//
//    @Pointcut("@annotation(com.vasylchenko.annotation.Loggable)")
//    public void loggableMethod() { }
//
////    @Before("console() && loggableMethod()")
////    public void calcLogger(ProceedingJoinPoint point) throws Throwable {
////        String methodName = point.getSignature().getName();
////        Object result = point.proceed();
////        System.out.println(methodName);
////        LOGGER.debug("Method " + methodName + " returns " + result);
////    }
//
//    @Before("bootstrap()")
//    public void test(ProceedingJoinPoint point){
////        LOGGER.debug("Method " + point.getSignature().getName());
//        System.out.println("Method " + point.getSignature().getName());
//    }

//        long start = System.currentTimeMillis();
//        Object result = point.proceed();
//        LOGGER.info(point.getSignature().getName() + " " + (System.currentTimeMillis() - start));
//        LOGGER.info(
//                "#%s(%s): %s in %[msec]s",
//                point.getSignature().getName(),
//                point.getArgs(),
//                result,
//                System.currentTimeMillis() - start
//        );
//        Object[] methodArgs = point.getArgs();
//        LOGGER.debug("Call method " + methodName + " with args " + Arrays.toString(methodArgs));