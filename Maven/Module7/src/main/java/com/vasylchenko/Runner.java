package com.vasylchenko;

import com.vasylchenko.gui.SpringFXMLLoader;
import com.vasylchenko.jdbc.dao.KitchenDAO;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Runner extends Application{

    private static final SpringFXMLLoader loader = new SpringFXMLLoader();

    static KitchenDAO kitchenDAO;

    public void setKitchenDAO(KitchenDAO kitchenDAO) {
        Runner.kitchenDAO = kitchenDAO;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) loader.load("/gui/RestraintDataScene.fxml");
        Scene scene = new Scene(root,700,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
