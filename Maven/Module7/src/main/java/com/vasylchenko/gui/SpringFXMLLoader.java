package com.vasylchenko.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFXMLLoader {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("configuration/spring/app-context.xml",
                    "configuration/hibernate/hibernate-context.xml");

    public Object load(String url) {
        try (InputStream fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
            loader.setControllerFactory(context::getBean);
            return (AnchorPane) loader.load(fxmlStream);
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(String.format("Failed to load FXML file '%s'", url));
        }
    }
}
