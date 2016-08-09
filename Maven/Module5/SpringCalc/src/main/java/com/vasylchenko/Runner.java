package com.vasylchenko;

import com.vasylchenko.annotation.Loggable;
import com.vasylchenko.configs.AppConfig;
import com.vasylchenko.configs.LoggingAspect;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    @Loggable
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, LoggingAspect.class);
        Bootstrap bootstrap = applicationContext.getBean(Bootstrap.class);
        bootstrap.run();
    }
}
