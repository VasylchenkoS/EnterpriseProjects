package com.vasylchenko.gui.controller;

import com.vasylchenko.gui.util.DateUtil;
import com.vasylchenko.jdbc.components.*;
import com.vasylchenko.jdbc.model.*;
import com.vasylchenko.jdbc.model.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RestraintDataSceneController implements Initializable {

    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private DishController dishController;
    @Autowired
    private MenuController menuController;
    @Autowired
    private StorageController storageController;
    @Autowired
    private OrderingController orderingController;
    @Autowired
    private KitchenController kitchenController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAllEmployee();
        showAllDish();
        showAllMenu();
        showAllStorageIngredients();
        showAllOpenOrder();
    }

//
//    ------------------------KITCHEN HISTORY-------------------------------------------
//

    @FXML
    private Button kitchenAddNewDishButton;

    @FXML
    private Button kitchenShowDishCookingButton;

    @FXML
    private Button kitchenShowAllReadyButton;

    @FXML
    private Button kitchenSetDishReadyButton;

    @FXML
    private TableView<Kitchen> kitchenTable;

    @FXML
    private TableColumn<Kitchen, ?> kitchenId;

    @FXML
    private TableColumn<Kitchen, ?> kitchenCookName;

    @FXML
    private TableColumn<Kitchen, ?> kitchenOrderNumber;

    @FXML
    private TableColumn<Kitchen, ?> kitchenDate;

    @FXML
    private TableColumn<Kitchen, ?> kitchenDishState;

    @FXML
    private TableColumn<Kitchen, ?> kitchenDishesNumber;

    @FXML
    void kitchenAddNewDishButtonPress(ActionEvent event) {
        List<String> list = new LinkedList<>();
        orderingController.showOpenOrder().forEach(ordering ->
        list.add(String.valueOf(ordering.getId())));
        ChoiceDialog<String> dialog = new ChoiceDialog<>("1", list);
        dialog.setTitle("Choice Dialog");
        dialog.setContentText("Choose order:");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(letter -> kitchenController.addAllDishFromOrder(Integer.parseInt(result.get())));
        showKitchen(kitchenController.getAllCookingDish());
    }

    @FXML
    void kitchenShowDishCookingButtonPress(ActionEvent event) {
        showKitchen(kitchenController.getAllCookingDish());
    }

    @FXML
    void kitchenShowAllReadyButtonPress(ActionEvent event) {
        showKitchen(kitchenController.getAllReadyDish());
    }

    @FXML
    void kitchenSetDishReadyButtonPress(ActionEvent event) {
        if (kitchenTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose row to update");
            alert.showAndWait();
        } else {
            kitchenController.setDishReady(kitchenTable.getItems().get(kitchenTable.getSelectionModel().getSelectedIndex()).getId());
            showKitchen(kitchenController.getAllReadyDish());
        }
    }


    private void showKitchen(List<Kitchen> kitchenList) {
        ObservableList<Kitchen> data = FXCollections.observableArrayList(kitchenList);
        kitchenId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kitchenCookName.setCellValueFactory(new PropertyValueFactory<>("cookName"));
        kitchenOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        kitchenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        kitchenDishState.setCellValueFactory(new PropertyValueFactory<>("status"));
        kitchenDishesNumber.setCellValueFactory(new PropertyValueFactory<>("dishName"));
        kitchenTable.setItems(data);
    }


//
//    ---------------------------ORDERING--------------------------------------------
//

    @FXML
    void orderDeleteButtonPress(ActionEvent event) {
        if (orderTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose order to remove");
            alert.showAndWait();
        } else {
            orderingController.deleteOrderById(orderTable.getItems().get(orderTable.getSelectionModel().getSelectedIndex()).getId());
            showOrder(orderingController.showOpenOrder());
        }
    }

    @FXML
    void orderShowClosedButtonPress(ActionEvent event) {
        showOrder(orderingController.showClosedOrder());
    }

    @FXML
    void orderShowOpenButtonPress(ActionEvent event) {
        showAllOpenOrder();
    }

    @FXML
    void orderAddDishButtonPress(ActionEvent event) {
        if (orderTable.getSelectionModel().isEmpty())  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose order for update Dish");
            alert.showAndWait();
        } else {
            Dialog<List<String>> dialog = new Dialog<>();
            ObservableList<String> data = FXCollections.observableArrayList(menuController.getAllDish());
            createDialogForMenu(dialog, data);
            Optional<List<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                orderingController.addDishToOrder(orderTable.getSelectionModel().selectedItemProperty().get(), result.get());
            }
            showAllOpenOrder();
        }
    }

    @FXML
    void orderRemoveDishButtonPress(ActionEvent event) {
        if (orderTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose order for update Dish");
            alert.showAndWait();
        } else {
            Dialog<List<String>> dialog = new Dialog<>();
            ObservableList<String> data = FXCollections.observableArrayList(orderingController.getDishInOrder(orderTable.getSelectionModel().selectedItemProperty().get().getId()));
            createDialogForMenu(dialog, data);
            Optional<List<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                orderingController.removeDishFromOrder(orderTable.getSelectionModel().selectedItemProperty().get(), result.get());
            }
            showAllOpenOrder();
        }
    }

    @FXML
    void orderCloseButtonPress(ActionEvent event) {
        if (orderTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose order for closing");
            alert.showAndWait();
        } else {
            orderingController.closeOrderById(orderTable.getItems().get(orderTable.getSelectionModel().getSelectedIndex()).getId());
            showOrder(orderingController.showClosedOrder());
        }
    }

    @FXML
    void orderNewButtonPress(ActionEvent event) {
        Dialog<Ordering> dialog = new Dialog<>();
        dialog.setTitle("New Order Dialog");
        dialog.setHeaderText("Enter data for Order");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField employeeName = new TextField();
        employeeName.setPromptText("Waite Surname");
        TextField table = new TextField();
        table.setPromptText("Table Number");

        grid.add(new Label("Waite Surname"), 0, 0);
        grid.add(employeeName, 1, 0);
        grid.add(new Label("Table Number"), 0, 1);
        grid.add(table, 1, 1);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        employeeName.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Ordering(employeeName.getText(),table.getText(),null,null);
            }
            return null;
        });
        Optional<Ordering> result = dialog.showAndWait();
        if (result.isPresent()){
            orderingController.addNewOrder(result.get());
        }
        showAllOpenOrder();
    }

    @FXML
    private Button orderNewButton;

    @FXML
    private Button orderShowClosedButton;

    @FXML
    private Button orderCloseButton;

    @FXML
    private Button orderAddMenuButton;

    @FXML
    private Button orderDeleteButton;

    @FXML
    private Button orderAddDishButton;

    @FXML
    private Button orderRemoveDishButton;

    @FXML
    private Button orderShowOpenButton;

    @FXML
    private TableView<Ordering> orderTable;

    @FXML
    private TableColumn<Ordering, ?> orderIDColumn;

    @FXML
    private TableColumn<Ordering, ?> orderNameColumn;

    @FXML
    private TableColumn<Ordering, ?> orderDishColumn;

    @FXML
    private TableColumn<Ordering, ?> orderDateColumn;

    @FXML
    private TableColumn<Ordering, ?> orderTableNumColumn;

    @FXML
    private TableColumn<Ordering, ?> orderStateColumn;

    void showAllOpenOrder(){
        showOrder(orderingController.showOpenOrder());
    }

    void showOrder(List<Ordering> orderings){
        ObservableList<Ordering> data = FXCollections.observableArrayList(orderings);
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderNameColumn.setCellValueFactory(new PropertyValueFactory<>("waiterName"));
        orderTableNumColumn.setCellValueFactory(new PropertyValueFactory<>("TableNumber"));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderDishColumn.setCellValueFactory(new PropertyValueFactory<>("dishList"));
        orderStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        orderTable.setItems(data);
    }
//
//    ----------------------------STORAGE--------------------------------------------
//

    @FXML
    private Button storageChangeIngrCountButton;

    @FXML
    private Button storageFindIngrButton;

    @FXML
    private Button storageAddIngrButton;

    @FXML
    private Button storageAllIngrButton;

    @FXML
    private Button storageLowIngrCountButton;

    @FXML
    private Button storageDelIngrButton;

    @FXML
    private TableView<Storage> storageTable;

    @FXML
    private TableColumn<Storage, ?> storageIngrName;

    @FXML
    private TableColumn<Storage, ?> storageIngrQuantity;

    @FXML
    private TableColumn<Storage, ?> storageId;

    @FXML
    void storageAddIngrButtonPress(ActionEvent event) {
        Dialog<Storage> dialog = new Dialog<>();
        dialog.setTitle("New Storage Dialog");
        dialog.setHeaderText("Enter data for Ingredient");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");

        TextField quantity = new TextField();
        name.setPromptText("Quantity");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 0, 1);
        grid.add(new Label("Quantity:"), 1, 0);
        grid.add(quantity, 1, 1);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Storage(name.getText(), Long.valueOf(quantity.getText()));
            }
            return null;
        });
        Optional<Storage> result = dialog.showAndWait();
        if (result.isPresent()){
            storageController.addNewIngredient(result.get());
        }
        showAllStorageIngredients();
    }

    @FXML
    void storageDelIngrButtonPress(ActionEvent event) {
        if (storageTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose Ingredient to remove");
            alert.showAndWait();
        } else {
            storageController.deleteIngredientById(storageTable.getItems().get(storageTable.getSelectionModel().getSelectedIndex()).getId());
            showAllStorageIngredients();
        }
        showAllStorageIngredients();
    }

    @FXML
    void storageFindIngrButtonPress(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find Ingredient by name");
        dialog.setContentText("Please enter Ingredient name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            showIngredient(storageController.findIngredientByName(result.get()));
        }
    }

    @FXML
    void storageChangeIngrCountButtonPress(ActionEvent event) {
        Dialog<Storage> dialog = new Dialog<>();
        dialog.setTitle("Change Ingredient Count Dialog");
        dialog.setHeaderText("Enter data for Ingredient");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        ObservableList<String> data =
                FXCollections.observableArrayList(
                        storageController.getAllIngredientName());

        ChoiceBox name = new ChoiceBox();
        name.setItems(data);

        TextField quantity = new TextField();
        quantity.setPromptText("Quantity");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 0, 1);
        grid.add(new Label("Quantity:"), 1, 0);
        grid.add(quantity, 1, 1);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        quantity.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Storage(name.getSelectionModel().selectedItemProperty().getValue().toString(), Long.valueOf(quantity.getText()));
            }
            return null;
        });
        Optional<Storage> result = dialog.showAndWait();
        if (result.isPresent()){
            storageController.changeIngredientCount(result.get());
        }
        showAllStorageIngredients();
    }

    @FXML
    void storageAllIngrButtonPress(ActionEvent event) {
        showAllStorageIngredients();
    }

    @FXML
    void storageLowIngrCountButtonPress(ActionEvent event) {
        List<Storage> storageList = storageController.checkIngredientCount();
        showIngredient(storageList);
    }

    void showAllStorageIngredients(){
        List<Storage> storageList = storageController.getAllIngredient();
        showIngredient(storageList);
    }

    private void showIngredient(List<Storage> storageList) {
        ObservableList<Storage> data = FXCollections.observableArrayList(storageList);
        storageId.setCellValueFactory(new PropertyValueFactory<>("id"));
        storageIngrName.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        storageIngrQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        storageTable.setItems(data);
    }

//
//    -----------------------------MENU---------------------------------------------
//

    @FXML
    private Button menuFindMenuButton;

    @FXML
    private Button menuDeleteDishMenuButton;

    @FXML
    private Button menuDeleteMenuButton;

    @FXML
    private Button menuAddDishMenuButton;

    @FXML
    private Button menuAllMenuButton;

    @FXML
    private TableView<Menu> menuTable;

    @FXML
    private TableColumn<Menu, String> menuName;

    @FXML
    private TableColumn<Menu, Integer> menuID;

    @FXML
    private TableColumn<Menu, ?> menuDish;

    @FXML
    void menuFindMenuButtonPress(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find Menu by name");
        dialog.setContentText("Please enter Menu name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            showMenu(menuController.findByName(result.get()));
        }
    }

    @FXML
    void menuDeleteMenuButtonPress(ActionEvent event) {
        if (menuTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose menu to remove");
            alert.showAndWait();
        } else {
            menuController.deleteMenuById(menuTable.getItems().get(menuTable.getSelectionModel().getSelectedIndex()).getId());
            showAllMenu();
        }
    }

    @FXML
    void menuAddDishMenuButtonPress(ActionEvent event) {
        if (menuTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose menu for update Dish");
            alert.showAndWait();
        } else {
            Dialog<List<String>> dialog = new Dialog<>();
            ObservableList<String> data = FXCollections.observableArrayList(menuController.getAllDish());
            createDialogForMenu(dialog, data);
            Optional<List<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                menuController.addDishIntoMenu(menuTable.getSelectionModel().selectedItemProperty().get(), result.get());
            }
            showAllMenu();
        }
    }

    private void createDialogForMenu(Dialog<List<String>> dialog, ObservableList<String> data) {
        dialog.setTitle("New Dish in Menu Dialog");
        dialog.setHeaderText("Chose Dish in Menu for adding(5 max in 1 request)");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ChoiceBox dish1 = new ChoiceBox();
        dish1.setItems(data);
        ChoiceBox dish2 = new ChoiceBox();
        dish2.setItems(data);
        ChoiceBox dish3 = new ChoiceBox();
        dish3.setItems(data);
        ChoiceBox dish4 = new ChoiceBox();
        dish4.setItems(data);
        ChoiceBox dish5 = new ChoiceBox();
        dish5.setItems(data);

        grid.add(new Label("Dish №1:"), 0, 0);
        grid.add(dish1, 1, 0);
        grid.add(new Label("Dish №2:"), 0, 1);
        grid.add(dish2, 1, 1);
        grid.add(new Label("Dish №3:"), 0, 2);
        grid.add(dish3, 1, 2);
        grid.add(new Label("Dish №4:"), 0, 3);
        grid.add(dish4, 1, 3);
        grid.add(new Label("Dish №5:"), 0, 4);
        grid.add(dish5, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                LinkedList list = new LinkedList<String>();
                if (!dish1.getSelectionModel().isEmpty())
                    list.add(dish1.getSelectionModel().selectedItemProperty().getValue().toString());
                if (!dish2.getSelectionModel().isEmpty())
                    list.add(dish2.getSelectionModel().selectedItemProperty().getValue().toString());
                if (!dish3.getSelectionModel().isEmpty())
                    list.add(dish3.getSelectionModel().selectedItemProperty().getValue().toString());
                if (!dish4.getSelectionModel().isEmpty())
                    list.add(dish4.getSelectionModel().selectedItemProperty().getValue().toString());
                if (!dish5.getSelectionModel().isEmpty())
                    list.add(dish5.getSelectionModel().selectedItemProperty().getValue().toString());
                return list;
            }
            return null;
        });
    }

    @FXML
    void menuDeleteDishMenuButtonPress(ActionEvent event) {
        if (menuTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose menu for update Dish");
            alert.showAndWait();
        } else {
            Dialog<List<String>> dialog = new Dialog<>();
            ObservableList<String> data = FXCollections.observableArrayList(menuController.getAllDishFromMenu(menuTable.getSelectionModel().selectedItemProperty().get()));
            createDialogForMenu(dialog, data);
            Optional<List<String>> result = dialog.showAndWait();
            if (result.isPresent()){
                menuController.deleteDishFromMenu(menuTable.getSelectionModel().selectedItemProperty().get(), result.get());
            }
            showAllMenu();
        }
    }

    @FXML
    void menuAllMenuButtonPress(ActionEvent event) {
        showAllMenu();
    }

    @FXML
    void menuAddMenuButtonPress(ActionEvent event) {
        Dialog<Menu> dialog = new Dialog<>();
        dialog.setTitle("New Menu Dialog");
        dialog.setHeaderText("Enter data for Menu");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Menu(name.getText(),null);
            }
            return null;
        });
        Optional<Menu> result = dialog.showAndWait();
        if (result.isPresent()){
            menuController.addNewMenu(result.get());
        }
        showAllMenu();
    }

    void showAllMenu(){
        showMenu(menuController.getAll());
    }

    void showMenu(List<Menu> menus){
        ObservableList<Menu> data = FXCollections.observableArrayList(menus);
        menuID.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuName.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        menuDish.setCellValueFactory(new PropertyValueFactory<>("dishList"));
        menuTable.setItems(data);
    }

//
//    -----------------------------DISH---------------------------------------------
//

    @FXML
    private Button dishFindButton;

    @FXML
    private Button dishRemoveButton;

    @FXML
    private Button dishNewButton;

    @FXML
    private Button dishAllButton;

    @FXML
    private TableView<Dish> dishTable;

    @FXML
    private TableColumn<Dish, Integer> dishId;

    @FXML
    private TableColumn<Dish, String> dishName;

    @FXML
    private TableColumn<Dish, Long> dishWeigth;

    @FXML
    private TableColumn<Dish, Double> dishPrice;

    @FXML
    private TableColumn<Dish, String> dishCategory;

    @FXML
    void dishNewButtonPress(ActionEvent event) {
        Dialog<Dish> dialog = new Dialog<>();
        dialog.setTitle("New Dish Dialog");
        dialog.setHeaderText("Enter data for Dish");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField name = new TextField();
        name.setPromptText("Name");
        ChoiceBox category = new ChoiceBox();
        ObservableList<String> data = FXCollections.observableArrayList(dishController.getAllCategory());
        category.setItems(data);
        TextField price = new TextField();
        price.setPromptText("Price");
        TextField weight = new TextField();
        weight.setPromptText("Weight");

        grid.add(new Label("Name:"), 0, 0);
        grid.add(name, 1, 0);
        grid.add(new Label("Category:"), 0, 1);
        grid.add(category, 1, 1);
        grid.add(new Label("Price:"), 0, 2);
        grid.add(price, 1, 2);
        grid.add(new Label("Weight:"), 0, 3);
        grid.add(weight, 1, 3);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });
        price.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Dish(name.getText(), category.getSelectionModel().selectedItemProperty().getValue().toString(),
                Double.parseDouble(price.getText()), Double.parseDouble(weight.getText()));
            }
            return null;
        });
        Optional<Dish> result = dialog.showAndWait();
        if (result.isPresent()){
            dishController.addNewDish(result.get());
        }
        showAllDish();
    }

    @FXML
    void dishRemoveButtonPress(ActionEvent event) {
        if (dishTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose dish to remove");
            alert.showAndWait();
        } else {
            dishController.deleteDishById(dishTable.getItems().get(dishTable.getSelectionModel().getSelectedIndex()).getId());
            showAllDish();
        }
    }

    @FXML
    void dishFindButtonPress(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find Dish by name");
        dialog.setContentText("Please enter Dish name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            showDish(dishController.getDishByName(result.get()));
        }
    }

    @FXML
    void dishAllButtonPress(ActionEvent event) {
        showAllDish();
    }

    void showAllDish(){
        showDish(dishController.getAllDish());
    }

    void showDish(List<Dish> dish){
        ObservableList<Dish> data = FXCollections.observableArrayList(dish);
        dishId.setCellValueFactory(new PropertyValueFactory<>("id"));
        dishName.setCellValueFactory(new PropertyValueFactory<>("name"));
        dishCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        dishPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        dishWeigth.setCellValueFactory(new PropertyValueFactory<>("weight"));
        dishTable.setItems(data);
    }
//
//    ---------------------------EMPLOYEE-------------------------------------------
//

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> employeePosition;

    @FXML
    private TableColumn<Employee, String> employeePhone;

    @FXML
    private TableColumn<Employee, String> employeeName;

    @FXML
    private TableColumn<Employee, Float> employeeSalary;

    @FXML
    private TableColumn<Employee, Integer> employeeId;

    @FXML
    private TableColumn<Employee, String> employeeSurname;

    @FXML
    private TableColumn<Employee, Date> employeeBirthday;

    @FXML
    private Button employeeNewButton;

    @FXML
    private Button employeeRemoveButton;

    @FXML
    private Button employeeFindButton;

    @FXML
    private Button employeeAllButton;

    @FXML
    void employeeAllButtonPress(ActionEvent event) {
        showAllEmployee();
    }

    @FXML
    void employeeFindButtonPress(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find Employee by name");
        dialog.setContentText("Please enter employee name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            showEmployee(employeeController.getEmployeeByName(result.get()));
        }
    }

    @FXML
    void employeeRemoveButtonPress(ActionEvent event) {
        if (employeeTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose employee to remove");
            alert.showAndWait();
        } else {
            employeeController.deleteEmployeeById(employeeTable.getItems().get(employeeTable.getSelectionModel().getSelectedIndex()).getId());
            showAllEmployee();
        }
    }

    @FXML
    void employeeNewButtonPress(ActionEvent event) {
        Dialog<Employee> dialog = new Dialog<>();
        dialog.setTitle("New Employee Dialog");
        dialog.setHeaderText("Enter data for employee");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField surname = new TextField();
        surname.setPromptText("Surname");
        TextField name = new TextField();
        name.setPromptText("Name");
        TextField birthday = new TextField();
        birthday.setPromptText("Birthday");
        TextField phone = new TextField();
        phone.setPromptText("Phone");

        ChoiceBox position = new ChoiceBox();
        ObservableList<String> data = FXCollections.observableArrayList(employeeController.getAllPositions());
        position.setItems(data);

        TextField salary = new TextField();
        salary.setPromptText("Salary");

        grid.add(new Label("Surname:"), 0, 0);
        grid.add(surname, 1, 0);
        grid.add(new Label("Name:"), 0, 1);
        grid.add(name, 1, 1);
        grid.add(new Label("Birthday:"), 0, 2);
        grid.add(birthday, 1, 2);
        grid.add(new Label("Phone:"), 0, 3);
        grid.add(phone, 1, 3);
        grid.add(new Label("Position:"), 0, 4);
        grid.add(position, 1, 4);
        grid.add(new Label("Salary:"), 0, 5);
        grid.add(salary, 1, 5);

        Node acceptButton = dialog.getDialogPane().lookupButton(addButtonType);
        acceptButton.setDisable(true);
        surname.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });
        name.textProperty().addListener((observable, oldValue, newValue) -> {
            acceptButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                return new Employee(name.getText(), surname.getText(), DateUtil.parseString(birthday.getText()), phone.getText(), position.getSelectionModel().selectedItemProperty().getValue().toString(), Float.valueOf(salary.getText()));
            }
            return null;
        });
        Optional<Employee> result = dialog.showAndWait();
        if (result.isPresent()){
            employeeController.addNewEmployee(result.get());
        }
        showAllEmployee();
    }

    void showAllEmployee(){
        showEmployee(employeeController.getAllEmployee());
    }

    void showEmployee(List<Employee> employee){
        ObservableList<Employee> data = FXCollections.observableArrayList(employee);
        employeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        employeeBirthday.setCellValueFactory(new PropertyValueFactory<>("birth"));
        employeePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        employeePosition.setCellValueFactory(new PropertyValueFactory<>("position"));
        employeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        employeeTable.setItems(data);
    }
}
