package com.vasylchenko.config;

import com.vasilchenko.Calculate;
import com.vasylchenko.beans.NewSimpleCalcDouble;
import com.vasylchenko.Bootstrap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Bootstrap javaRunner(Calculate calculate){
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setCalculate(calculate);
        return bootstrap;
    }

    @Bean
    @Scope("prototype")
    public Calculate startCalculate(){
        return new Calculate(new NewSimpleCalcDouble());
    }
}
