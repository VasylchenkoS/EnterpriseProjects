package com.vasylchenko;

import com.vasilchenko.Calculate;
import com.vasylchenko.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Bootstrap {

    private Calculate calculate;

    public void setCalculate(Calculate calculate) {
        this.calculate = calculate;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        Bootstrap bootstrap = applicationContext.getBean(Bootstrap.class);
        try {
            bootstrap.calculate.init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
