package com.vasylchenko;

import com.vasylchenko.configs.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        applicationContext.getBean(Bootstrap.class);
    }
}
