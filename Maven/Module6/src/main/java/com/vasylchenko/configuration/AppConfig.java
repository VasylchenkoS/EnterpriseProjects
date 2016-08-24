package com.vasylchenko.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.vasylchenko.Runner;
import com.vasylchenko.gui.controller.RestraintDataSceneController;
import com.vasylchenko.jdbc.components.*;
import com.vasylchenko.jdbc.jdbc_dao.*;
import com.vasylchenko.jdbc.model.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource(value={"classpath:jdbc/jdbc.properties"},
        ignoreResourceNotFound = true)
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan
public class AppConfig implements TransactionManagementConfigurer {

//    model configurator

//    Employee
    @Bean
    public JdbcEmployeeDAO employeeDAO(DataSource dataSources) throws PropertyVetoException {
        JdbcEmployeeDAO employeeDAO = new JdbcEmployeeDAO();
        employeeDAO.setDataSource(dataSources);
        return employeeDAO;
    }

    @Bean
    public EmployeeController employeeController(EmployeeDAO employeeDAO){
        EmployeeController employeeController = new EmployeeController();
        employeeController.setEmployeeDAO(employeeDAO);
        return employeeController;
    }

//DISH
    @Bean
    public JdbcDishDAO dishDAO(DataSource dataSource){
        JdbcDishDAO dishDAO = new JdbcDishDAO();
        dishDAO.setDataSource(dataSource);
        return dishDAO;
    }

    @Bean
    public DishController dishController(DishDAO dishDAO){
        DishController dishController = new DishController();
        dishController.setDishDAO(dishDAO);
        return dishController;
    }
// Menu

    @Bean
    public JdbcMenuDAO menuDAO(DataSource dataSource){
        JdbcMenuDAO menuDAO = new JdbcMenuDAO();
        menuDAO.setDataSource(dataSource);
        return menuDAO;
    }

    @Bean
    public MenuController menuController(MenuDAO menuDAO){
        MenuController menuController = new MenuController();
        menuController.setMenuDAO(menuDAO);
        return menuController;
    }

//    Storage

    @Bean
    public JdbcStorageDAO storageDAO(DataSource dataSource){
        JdbcStorageDAO storageDAO = new JdbcStorageDAO();
        storageDAO.setDataSource(dataSource);
        return storageDAO;
    }

    @Bean
    public StorageController storageController(StorageDAO storageDAO) {
        StorageController storageController = new StorageController();
        storageController.setStorageDAO(storageDAO);
        return storageController;
    }

//   Ordering

    @Bean
    public JdbcOrderingDAO orderingDAO(DataSource dataSource){
        JdbcOrderingDAO orderingDAO = new JdbcOrderingDAO();
        orderingDAO.setDataSource(dataSource);
        return orderingDAO;
    }

    @Bean
    public OrderingController orderingController(OrderingDAO orderingDAO){
        OrderingController orderingController = new OrderingController();
        orderingController.setOrderingDAO(orderingDAO);
        return orderingController;
    }

//    Kitchen

    @Bean
    public JdbcKitchenDAO kitchenDAO(DataSource dataSource){
        JdbcKitchenDAO kitchenDAO =new JdbcKitchenDAO();
        kitchenDAO.setDataSource(dataSource);
        return kitchenDAO;
    }

    @Bean
    public KitchenController kitchenController(){
        return new KitchenController();
    }

//    API Configure

    @Bean
    public Runner runner() {
        return new Runner();
    }

    @Bean
    public RestraintDataSceneController dataSceneController(){
        return new RestraintDataSceneController();
    }

//    SQL Configure
    @Value("${jdbc.driver.class}")
    private String jdbcDriverClass;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.user}")
    private String jdbcUser;
    @Value("${jdbc.password}")
    private String jdbcPassword;
    @Value("${jdbc.max.connections}")
    private int jdbcMaxCon;
    @Value("${jdbc.min.connections}")
    private int jdbcMinCon;
    @Value("${jdbc.acquire.increment}")
    private int jdbcAcquireIncrement;

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUser);
        dataSource.setPassword(jdbcPassword);
        dataSource.setMaxPoolSize(jdbcMaxCon);
        dataSource.setMinPoolSize(jdbcMinCon);
        dataSource.setAcquireIncrement(jdbcAcquireIncrement);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager txManager() throws PropertyVetoException {
        DataSourceTransactionManager tmDataSource = new DataSourceTransactionManager();
        tmDataSource.setDataSource(dataSource());
        return tmDataSource;
    }


    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return txManager();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
