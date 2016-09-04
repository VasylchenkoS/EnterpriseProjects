package com.vasylchenko.gui.controller;

import com.vasylchenko.gui.util.DateUtil;
import com.vasylchenko.jdbc.components.*;
import com.vasylchenko.jdbc.model.*;
import com.vasylchenko.jdbc.model.Menu;
import javafx.beans.property.SimpleStringProperty;
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

import java.net.URL;
import java.sql.Date;
import java.util.*;

public class RestraintDataSceneController implements Initializable {

    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;
    private StorageController storageController;
    private OrderingController orderingController;
    private KitchenController kitchenController;
    private CategoryController categoryController;
    private PositionController positionController;
    private TablesController tablesController;
    private OrderStateController orderStateController;

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setStorageController(StorageController storageController) {
        this.storageController = storageController;
    }

    public void setOrderingController(OrderingController orderingController) {
        this.orderingController = orderingController;
    }

    public void setKitchenController(KitchenController kitchenController) {
        this.kitchenController = kitchenController;
    }

    public void setCategoryController(CategoryController categoryController) {
        this.categoryController = categoryController;
    }

    public void setPositionController(PositionController positionController) {
        this.positionController = positionController;
    }

    public void setTablesController(TablesController tablesController) {
        this.tablesController = tablesController;
    }

    public void setOrderStateController(OrderStateController orderStateController) {
        this.orderStateController = orderStateController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showAllEmployee();
        showAllDish();
        showAllMenu();
        showAllStorageIngredients();
        showAllOpenOrder();
        showAllKitchen();
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
    private TableColumn<? extends Kitchen, ?> kitchenId;

    @FXML
    private TableColumn<? extends Kitchen, String> kitchenCookName;

    @FXML
    private TableColumn<? extends Kitchen, String> kitchenOrderNumber;

    @FXML
    private TableColumn<? extends Kitchen, ?> kitchenDate;

    @FXML
    private TableColumn<? extends Kitchen, String> kitchenDishState;

    @FXML
    private TableColumn<? extends Kitchen, String> kitchenDishName;

    @FXML
    void kitchenAddNewDishButtonPress(ActionEvent event) {
        Dialog<Ordering> dialog = new Dialog<>();
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Select Order");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ObservableList<Ordering> data =
                FXCollections.observableArrayList(orderingController.showOpenOrder());

        ComboBox<Ordering> name = new ComboBox<>(data);
        name.setCellFactory(call -> new ListCell<Ordering>(){
            @Override
            protected void updateItem(Ordering t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) setText(String.valueOf(t.getId()));
                else setText(null);
            }
        });
        grid.add(new Label("Order ID:"), 0, 0);
        grid.add(name, 0, 1);

        ObservableList<Cook> dataEmployee =
                FXCollections.observableArrayList(employeeController.getAllCooks());


        ComboBox<Cook> employeeName = new ComboBox<>(dataEmployee);
        employeeName.setCellFactory(call -> new ListCell<Cook>(){
            @Override
            protected void updateItem(Cook t, boolean bln) {
                super.updateItem(t, bln);
                if (t != null) setText(String.valueOf(t.getSurname() + t.getName()));
                else setText(null);
            }
        });
        grid.add(new Label("Cook Name:"), 1, 0);
        grid.add(employeeName, 1, 1);

        dialog.getDialogPane().setContent(grid);
//        Ordering ordering;
//        Cook employee;
//        dialog.setResultConverter(dialogButton -> {
//            if (dialogButton == addButtonType) {
//                ordering = name.getSelectionModel().getSelectedItem();
//
//                return employeeName.getSelectionModel().getSelectedItem();
//            }
//            return null;
//        });

        Optional<Ordering> result = dialog.showAndWait();
        result.ifPresent(letter -> kitchenController.addAllDishFromOrder(result.get()));

        showKitchen(kitchenController.getAllCookingDish());
        showAllStorageIngredients();
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
            kitchenController.setDishReady(
                    kitchenTable.getSelectionModel().getSelectedItem());
            showKitchen(kitchenController.getAllReadyDish());
        }
    }

    private void showAllKitchen(){
        showKitchen(kitchenController.getAllCookingDish());
    }

    private void showKitchen(List<? extends Kitchen> kitchenList) {
        ObservableList<Kitchen> data = FXCollections.observableArrayList(kitchenList);
        kitchenId.setCellValueFactory(new PropertyValueFactory<>("id"));
        kitchenCookName.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getCook().getName() + " " +
                                                    param.getValue().getCook().getSurname()));
        kitchenOrderNumber.setCellValueFactory(
                param -> new SimpleStringProperty(String.valueOf(param.getValue().getOrder().getId())));
        kitchenDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        kitchenDishState.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getStatus().getState()));
        kitchenDishName.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getDishName().getName())
        );
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
            orderingController.deleteOrder(orderTable.getSelectionModel().getSelectedItem());
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
            Dialog<List<Dish>> dialog = new Dialog<>();
            List<Dish> dishList = dishController.getAllDish();
            ObservableList<Dish> data = FXCollections.observableArrayList(dishList);
            createDialogForMenu(dialog, data);
            Optional<List<Dish>> result = dialog.showAndWait();
            if (result.isPresent()){
                orderingController.addDishesInOrder(
                        orderTable.getSelectionModel().getSelectedItem(), result.get());
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
            Dialog<List<Dish>> dialog = new Dialog<>();
            ObservableList<Dish> data = FXCollections.observableArrayList(
                            orderTable.getSelectionModel().selectedItemProperty().get().getDishList());
            createDialogForMenu(dialog, data);
            Optional<List<Dish>> result = dialog.showAndWait();
            if (result.isPresent()){
                orderingController.removeDishesFromOrder(
                        orderTable.getSelectionModel().selectedItemProperty().get(), result.get());
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
            orderingController.closeOrder(orderTable.getSelectionModel().getSelectedItem());
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
                return new Ordering(employeeController.getEmployeeBySurname(employeeName.getText())
                        ,tablesController.getTableByName(table.getText()), new Date(new java.util.Date().getTime()),
                        null,orderStateController.getStateByName("Open"));
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
    private TableColumn<Ordering, String> orderNameColumn;

    @FXML
    private TableColumn<Ordering, String> orderDishColumn;

    @FXML
    private TableColumn<Ordering, ?> orderDateColumn;

    @FXML
    private TableColumn<Ordering, String> orderTableNumColumn;

    @FXML
    private TableColumn<Ordering, String> orderStateColumn;

    void showAllOpenOrder(){
        showOrder(orderingController.showOpenOrder());
    }

    void showOrder(List<Ordering> orderings){
        ObservableList<Ordering> data = FXCollections.observableArrayList(orderings);
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        orderNameColumn.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getEmployee().getSurname() + " " +
                        param.getValue().getEmployee().getName()));
        orderTableNumColumn.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getTable().getNumber()));
        orderDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        orderDishColumn.setCellValueFactory(
                param -> {
                    List<String> dishes = new ArrayList<String>();
                    param.getValue().getDishList().forEach(dish ->
                            dishes.add(dish.getName()));
                    return new SimpleStringProperty(dishes.toString());
                });
        orderStateColumn.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getOrderState().getState()));
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
            showIngredient(new ArrayList<>(Arrays.asList(storageController.getIngredientByName(result.get()))));
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
        ObservableList<Storage> data =
                FXCollections.observableArrayList(storageController.getAllIngredient());

        ComboBox<Storage> name = new ComboBox<>(data);
        name.setCellFactory(call -> new ListCell<Storage>(){
                    @Override
                    protected void updateItem(Storage t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) setText(t.getIngredientName());
                        else setText(null);
                    }
                });
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
                return name.getSelectionModel().getSelectedItem();
            }
            return null;
        });
        Optional<Storage> result = dialog.showAndWait();
        if (result.isPresent()){
            storageController.changeIngredientCount(result.get(), Long.valueOf(quantity.getText()));
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
    private Button menuDeleteMenuButton;

    @FXML
    private Button menuUpdateDishMenuButton;

    @FXML
    private Button menuAllMenuButton;

    @FXML
    private TableView<Menu> menuTable;

    @FXML
    private TableColumn<Menu, String> menuName;

    @FXML
    private TableColumn<Menu, Integer> menuID;

    @FXML
    private TableColumn<Menu, String> menuDish;

    @FXML
    void menuFindMenuButtonPress(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Find");
        dialog.setHeaderText("Find Menu by name");
        dialog.setContentText("Please enter Menu name:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            showMenu(new ArrayList<>(Arrays.asList(menuController.findByName(result.get()))));
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
    void menuUpdateDishMenuButtonPress(ActionEvent event) {
        if (menuTable.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Choose menu for update Dish");
            alert.showAndWait();
        } else {
            Dialog<List<Dish>> dialog = new Dialog<>();

            ObservableList<Dish> data = FXCollections.observableArrayList(dishController.getAllDish());
            createDialogForMenu(dialog, data);
            Optional<List<Dish>> result = dialog.showAndWait();
            if (result.isPresent()){
                Menu menu = menuTable.getSelectionModel().selectedItemProperty().get();
                menuController.updateDishesInMenu(menu, new LinkedHashSet<>(result.get()));
            }
            showAllMenu();
        }
    }

    private static final class DishListCell extends ListCell<Dish> {
        @Override
        protected void updateItem(Dish t, boolean bln) {
            super.updateItem(t, bln);
            if (t != null) setText(t.getName());
            else setText(null);
        }
    }

    private void createDialogForMenu(Dialog<List<Dish>> dialog, ObservableList<Dish> data) {
        dialog.setTitle("New Dish in Menu Dialog");
        dialog.setHeaderText("Chose Dish in Menu for adding(5 max in 1 request)");

        ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<Dish> dish1 = new ComboBox<Dish>(data);
        dish1.setCellFactory(call -> new DishListCell());
        ComboBox<Dish> dish2 = new ComboBox<Dish>(data);
        dish2.setCellFactory(call -> new DishListCell());
        ComboBox<Dish> dish3 = new ComboBox<Dish>(data);
        dish3.setCellFactory(call -> new DishListCell());
        ComboBox<Dish> dish4 = new ComboBox<Dish>(data);
        dish4.setCellFactory(call -> new DishListCell());
        ComboBox<Dish> dish5 = new ComboBox<Dish>(data);
        dish5.setCellFactory(call -> new DishListCell());

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
                LinkedList<Dish> list = new LinkedList<>();
                if (!dish1.getSelectionModel().isEmpty())
                    list.add(dish1.getSelectionModel().selectedItemProperty().getValue());
                if (!dish2.getSelectionModel().isEmpty())
                    list.add(dish2.getSelectionModel().selectedItemProperty().getValue());
                if (!dish3.getSelectionModel().isEmpty())
                    list.add(dish3.getSelectionModel().selectedItemProperty().getValue());
                if (!dish4.getSelectionModel().isEmpty())
                    list.add(dish4.getSelectionModel().selectedItemProperty().getValue());
                if (!dish5.getSelectionModel().isEmpty())
                    list.add(dish5.getSelectionModel().selectedItemProperty().getValue());
                return list;
            }
            return null;
        });
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
        showMenu(menuController.getAllMenu());
    }

    void showMenu(List<Menu> menus){
        ObservableList<Menu> data = FXCollections.observableArrayList(menus);
        menuID.setCellValueFactory(new PropertyValueFactory<>("id"));
        menuName.setCellValueFactory(new PropertyValueFactory<>("menuName"));
        menuDish.setCellValueFactory(
                param -> {
                    List<String> result = new ArrayList<String>();
                    param.getValue().getDishSet().forEach(dish ->
                    result.add(dish.getName()));
                    return new SimpleStringProperty(result.toString());
                });
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
        ChoiceBox<String> category = new ChoiceBox<String>();

        List<String> categoryNamesList = new ArrayList<>();
        categoryController.getAllCategory().forEach(categoryName ->
        categoryNamesList.add(categoryName.getCategory()));

        ObservableList<String> data = FXCollections.observableArrayList(categoryNamesList);
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
                Category dishCategory = categoryController.getCategoryByName(
                        category.getSelectionModel().getSelectedItem());
                return new Dish(name.getText(), dishCategory,
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
            showDish(new ArrayList<>(Arrays.asList(dishController.getDishByName(result.get()))));
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
        dishCategory.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getCategory().getCategory()));
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
        if (result.isPresent()) {
            showEmployee(new ArrayList<>(Arrays.asList(employeeController.getEmployeeBySurname(result.get()))));
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

        ChoiceBox<String> position = new ChoiceBox<String>();

        List<String> positionsList = new ArrayList<>();
        positionController.getAllPosition().forEach(positions ->
        positionsList.add(positions.getPosition()));
        ObservableList<String> data = FXCollections.observableArrayList(positionsList);
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
                return new Employee(
                        name.getText(), surname.getText(), DateUtil.parseString(birthday.getText()), phone.getText(),
                        positionController.getPositionByName(position.getSelectionModel().selectedItemProperty().getValue()),
                        Float.valueOf(salary.getText()));
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

    void showEmployee(List<Employee> employee) {
        ObservableList<Employee> data = FXCollections.observableArrayList(employee);
        employeeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        employeeName.setCellValueFactory(new PropertyValueFactory<>("name"));
        employeeSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        employeeBirthday.setCellValueFactory(new PropertyValueFactory<>("birth"));
        employeePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        employeePosition.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getPosition().getPosition()));
        employeeSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        employeeTable.setItems(data);
    }
}
