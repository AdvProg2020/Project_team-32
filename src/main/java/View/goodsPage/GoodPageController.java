package View.goodsPage;

import Controller.GoodController;
import Model.Category;
import Model.Good;
import View.Updatable;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //categoriesTreeView.setRoot(Category.rootCategory.getCategory());
        TreeItem<String> category = new TreeItem<>("Categories");
        Category.rootCategory.getCategory(category);
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
                    for (String property : Category.getCategoryByName(selectedCategory).getSpecialProperties()) {
                        FiltersVBox.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), GoodController.getGoodController()));
                    }
                    FiltersVBox.getChildren().add(filterByGeneralProperties);
                    for (String property : Category.getGeneralProperties()) {
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
        for (int i = 0; i < GoodController.getGoodController().getSelectedGoods().size(); i++) {
            if (i % 2 == 0) {
                firstColumnGoods.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            } else {
                secondColumnGood.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            }
        }
        for (Good good : GoodController.getGoodController().getSelectedGoods()) {
            System.out.println(good);
        }
    }

    @Override
    public String valueOfFileter() {
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
