package com.vasylchenko.gui;

import com.vasylchenko.configuration.AppConfig;
import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFXMLLoader {

    private static final ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    public Object load(String url) {
        try (InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url)){
//            System.err.println(SpringFXMLLoader.class.getResource(url));
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            loader.setControllerFactory(context::getBean);
            return loader.load(fxmlStream);
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Failed to load FXML file '%s'", url));
        }
    }
}
