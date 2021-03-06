package com.vasylchenko.configs;

import com.vasylchenko.calclibrary.interfaces.OperationProvider;
import com.vasylchenko.calclibrary.interfaces.Parser;
import com.vasylchenko.calclibrary.sources.BaseOperationProvider;
import com.vasylchenko.calclibrary.sources.Calculator;
import com.vasylchenko.calclibrary.sources.ExpressionParser;
import com.vasylchenko.Bootstrap;
import com.vasylchenko.beans.operations.Dividing;
import com.vasylchenko.beans.operations.Multiplying;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Bootstrap bootstrap(Calculator calculator) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setCalculator(calculator);
        return bootstrap;
    }

    @Bean
    public Calculator calculator(OperationProvider operationProvider, Parser parser) {
        Calculator calculator = new Calculator();
        calculator.setOperationList(operationProvider.getAllOperations());
        calculator.setParser(parser);
        return calculator;
    }

    @Bean
    public OperationProvider initProvider(OperationProvider operationProvider) {
        operationProvider.init();
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
