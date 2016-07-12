package com.vasylchenko;

import com.vasylchenko.implementations.IntegerTaskProvider;
import com.vasylchenko.implementations.SerialExecutor;
import com.vasylchenko.interfaces.Executor;
import com.vasylchenko.interfaces.TaskProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public BootstrapWithJava bootstrapWithJava(ExecutorFactory executorFactory, TaskProvider<Integer> integerTaskProvider){
        BootstrapWithJava bootstrapWithJava = new BootstrapWithJava();
        bootstrapWithJava.setExecutorFactory(executorFactory);
        bootstrapWithJava.setTaskProvider(integerTaskProvider);
        return bootstrapWithJava;
    }

    @Bean
    public TaskProvider<Integer> integerTaskProvider(){
        IntegerTaskProvider integerTaskProvider = new IntegerTaskProvider();
        integerTaskProvider.init();
        return integerTaskProvider;
    }

    @Bean
    @Scope("prototype")
    public SerialExecutor<Integer> serialExecutor(){
        return new SerialExecutor<>();
    }

    @Bean
    public ExecutorFactory executorFactory(){
        return new ExecutorFactory() {
            @Override
            public Executor<Integer> getIntegerExecutor() {
                return serialExecutor();
            }
        };
    }
}
