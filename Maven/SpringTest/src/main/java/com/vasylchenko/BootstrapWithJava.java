package com.vasylchenko;

import com.vasylchenko.interfaces.Executor;
import com.vasylchenko.interfaces.TaskProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootstrapWithJava {

    private TaskProvider<Integer> taskProvider;
    private ExecutorFactory executorFactory;

    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
//        BootstrapWithXML bootstrapWithXML = (BootstrapWithXML) applicationContext.getBean("bootstrapWithXML");
        BootstrapWithJava bootstrapWithJava = applicationContext.getBean(BootstrapWithJava.class);
//        BootstrapWithJava bootstrapWithJava = applicationContext.getBean("bootstrap",BootstrapWithJava.class);
        bootstrapWithJava.execute();
        bootstrapWithJava.execute();
    }

    public void execute() {
        Executor<Integer> executor = executorFactory.getIntegerExecutor();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();
        System.out.println("[ValidResult:]");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("[InvalidResult:]");
        executor.getInvalidResults().forEach(System.out::println);
    }
}
