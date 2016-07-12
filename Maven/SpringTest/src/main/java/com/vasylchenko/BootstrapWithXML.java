package com.vasylchenko;

public class BootstrapWithXML {
/*
    private TaskProvider<Integer> taskProvider;
    private ExecutorFactory executorFactory;

    public void setTaskProvider(TaskProvider<Integer> taskProvider) {
        this.taskProvider = taskProvider;
    }

    public void setExecutorFactory(ExecutorFactory executorFactory) {
        this.executorFactory = executorFactory;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
//        BootstrapWithXML bootstrapWithXML = (BootstrapWithXML) applicationContext.getBean("bootstrapWithXML");
//        BootstrapWithXML bootstrapWithXML = applicationContext.getBean(BootstrapWithXML.class);
        BootstrapWithXML bootstrapWithXML = applicationContext.getBean("bootstrap",BootstrapWithXML.class);
        bootstrapWithXML.execute();
        bootstrapWithXML.execute();
    }

    public void execute() {
        Executor<Integer> executor = executorFactory.getIntegerExecutor();
        taskProvider.getAllTasks().forEach(executor::addTask);
        executor.execute();
        System.out.println("[ValidResult:]");
        executor.getValidResults().forEach(System.out::println);
        System.out.println("[InvalidResult:]");
        executor.getInvalidResults().forEach(System.out::println);
    }*/
}
