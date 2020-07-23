package View.goodsPage;

import Server.Controller.GoodController;
import Server.Model.Category;
import Server.Model.Good;
import View.Client;
import View.Updatable;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GoodPageController implements Initializable, Updatable {

    @FXML
    private TreeView<String> categoriesTreeView;

    @FXML
    private VBox FiltersVBox;

    @FXML
    private ScrollPane goodsPand;

    @FXML
    private HBox goodsBox;

    @FXML
    private VBox firstColumnGoods;

    @FXML
    private VBox secondColumnGood;
    @FXML CheckBox checkBox;


    private ObjectInputStream inputStream;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FiltersVBox.setAlignment(Pos.TOP_CENTER);
        TreeItem<String> category = new TreeItem<>("category");
        Client.sendMessage("get all categories for good page", new HashMap<>());
        ((Category) Client.getMessage().get("category")).getCategory(category);
        categoriesTreeView.setRoot(category);
        categoriesTreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(categoriesTreeView.getSelectionModel().getSelectedItem() != null) {
                    FiltersVBox.getChildren().clear();
                    String selectedCategory = categoriesTreeView.getSelectionModel().getSelectedItem().getValue();
                    Label filters = new Label("filters");
                    Label filterByCategoryProperties = new Label("Category Properties");
                    Label filterByGeneralProperties = new Label("General Properties");
                    FiltersVBox.getChildren().add(filters);
                    FiltersVBox.getChildren().add(filterByCategoryProperties);
                    HashMap<String, Object> arg1 = new HashMap<>();
                    arg1.put("selected category", selectedCategory);
                    Client.sendMessage("get special properties", arg1);
                    List<String> specialProperties = (List<String>) Client.getMessage().get("special properties");
                    for (String property : specialProperties) {
                        FiltersVBox.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), GoodController.getGoodController()));
                    }
                    FiltersVBox.getChildren().add(filterByGeneralProperties);
                    Client.sendMessage("get general properties", arg1);
                    List<String> generalProperties = (List<String>)Client.getMessage().get("general properties");
                    for (String property : generalProperties) {
                        FiltersVBox.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), GoodController.getGoodController()));
                    }
                }
            }
        });
        updateGoods();
    }

    @Override
    public Updatable getController() {
        return this;
    }

    @Override
    public void updateGoods() {

        firstColumnGoods.setSpacing(10);
        secondColumnGood.setSpacing(10);
        firstColumnGoods.getChildren().clear();
        secondColumnGood.getChildren().clear();

        List<Good> selectedGoods;
        Client.sendMessage("get selected goods", new HashMap<>());
        selectedGoods = (List<Good>) Client.getMessage().get("selected goods");

        for (int i = 0; i < selectedGoods.size(); i++) {
            if (i % 2 == 0) {
                firstColumnGoods.getChildren().add(GoodIconFactory.createIcon(selectedGoods.get(i)));
            } else {
                secondColumnGood.getChildren().add(GoodIconFactory.createIcon(selectedGoods.get(i)));
            }
        }
    }

    @Override
    public String valueOfFilter() {
        final String[] returnedValue = new String[1];
        Stage window = new Stage();
        window.setTitle("enter value of filter");
        TextField value = new TextField();
        Button button = new Button("submit");
        button.setOnAction(e -> {
            returnedValue[0] = value.getText();
            window.close();
        });
        VBox box = new VBox(value, button);
        Scene scene = new Scene(box);
        window.setScene(scene);
        window.showAndWait();
        return returnedValue[0];
    }

}
