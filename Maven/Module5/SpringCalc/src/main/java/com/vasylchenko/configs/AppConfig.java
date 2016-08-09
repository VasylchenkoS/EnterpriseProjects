package com.vasylchenko.configs;

import com.vasilchenko.interfaces.OperationProvider;
import com.vasilchenko.interfaces.Parser;
import com.vasilchenko.sources.BaseOperationProvider;
import com.vasilchenko.sources.Calculator;
import com.vasilchenko.sources.ExpressionParser;
import com.vasylchenko.Bootstrap;
import com.vasylchenko.beans.operations.Dividing;
import com.vasylchenko.beans.operations.Multiplying;
import org.springframework.context.annotation.*;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = LoggingAspect.class)
public class AppConfig {


    @Bean(initMethod = "run")
    public Bootstrap bootstrap(Calculator calculator) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setCalculator(calculator);
        return bootstrap;
    }

    @Bean
    @Scope("prototype")
    public Calculator calculator(OperationProvider operationProvider, Parser parser) {
        Calculator calculator = new Calculator();
        calculator.setOperationList(operationProvider.getAllOperations());
        calculator.setParser(parser);
        return calculator;
    }

    @Bean(initMethod = "init")
    public OperationProvider initProvider(OperationProvider operationProvider) {
        operationProvider.initAdditionalOperation(new Dividing());
        operationProvider.initAdditionalOperation(new Multiplying());
        return operationProvider;
    }

    @Bean
    public OperationProvider operationProvider() {
        return new BaseOperationProvider();
    }

    @Bean
    public Parser parser() {
        return new ExpressionParser();
    }

}
