package com.vasylchenko;

import org.springframework.stereotype.Component;

@Component
public class BootstrapWithParameters {

    /*private TaskProvider<Integer> taskProvider;
//    private ExecutorFactory executorFactory;
    private ObjectFactory<Executor<Integer>> executorFactory;

    @Autowired
    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    public void setExecutorFactory(ObjectFactory<Executor<Integer>> executorFactory) {
        this.executorFactory = executorFactory;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
//        BootstrapWithXML bootstrap = (BootstrapWithXML) applicationContext.getBean("bootstrap");
//        BootstrapWithXML bootstrap = applicationContext.getBean(BootstrapWithXML.class);
        BootstrapWithParameters bootstrapWithParameters = applicationContext.getBean("bootstrapWithParameters", BootstrapWithParameters.class);
        bootstrapWithParameters.execute();
        bootstrapWithParameters.execute();
    }

    public void execute() {
        Executor<Integer> executor = executorFactory.getObject();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();
        System.out.println("[ValidResult:]");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("[InvalidResult:]");
        executor.getInvalidResults().forEach(System.out::println);
    }*/
}
