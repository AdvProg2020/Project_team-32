package View.OffPageController;

import Server.Controller.GoodController;
import Server.Controller.OffController;
import Server.Model.Category;
import Server.Model.Good;
import View.Client;
import View.Updatable;
import View.goodsPage.FilterItemFactory;
import View.goodsPage.GoodIconFactory;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OffPageController implements Initializable, Updatable {
    @FXML
    private TreeView<String> categoriesTreeView1;

    @FXML
    private VBox FiltersVBox1;

    @FXML
    private ScrollPane goodsPand1;

    @FXML
    private HBox goodsBox1;

    @FXML
    private VBox firstColumnGoods1;

    @FXML
    private VBox secondColumnGood1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeItem<String> category = new TreeItem<>("category");
        Client.sendMessage("get all categories for good page", new HashMap<>());
        ((Category) Client.getMessage().get("category")).getCategory(category);
        categoriesTreeView1.setRoot(category);
        categoriesTreeView1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(categoriesTreeView1.getSelectionModel().getSelectedItem() != null) {
                    FiltersVBox1.getChildren().clear();
                    String selectedCategory = categoriesTreeView1.getSelectionModel().getSelectedItem().getValue();
                    Label filters = new Label("filters");
                    Label filterByCategoryProperties = new Label("Category Properties");
                    Label filterByGeneralProperties = new Label("General Properties");

                    FiltersVBox1.getChildren().add(filterByCategoryProperties);

                    HashMap<String, Object> arg1 = new HashMap<>();
                    arg1.put("selected category", selectedCategory);
                    Client.sendMessage("get special properties", arg1);
                    List<String> specialProperties = (List<String>) Client.getMessage().get("special properties");
                    for (String property : specialProperties) {
                        FiltersVBox1.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), GoodController.getGoodController()));
                    }

                    FiltersVBox1.getChildren().add(filterByGeneralProperties);

                    Client.sendMessage("get general properties", arg1);
                    List<String> generalProperties = (List<String>)Client.getMessage().get("general properties");
                    for (String property : generalProperties) {
                        FiltersVBox1.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), GoodController.getGoodController()));
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
        firstColumnGoods1.setSpacing(10);
        secondColumnGood1.setSpacing(10);
        firstColumnGoods1.getChildren().clear();
        secondColumnGood1.getChildren().clear();

        List<Good> selectedGoods;
        Client.sendMessage("get selected goods in off controller", new HashMap<>());
        selectedGoods = (List<Good>) Client.getMessage().get("selected goods");

        for (int i = 0; i < selectedGoods.size(); i++) {
            if (i % 2 == 0) {
                firstColumnGoods1.getChildren().add(GoodIconFactory.createIcon(selectedGoods.get(i)));
            } else {
                secondColumnGood1.getChildren().add(GoodIconFactory.createIcon(selectedGoods.get(i)));
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
