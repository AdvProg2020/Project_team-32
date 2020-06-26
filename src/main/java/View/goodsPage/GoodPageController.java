package View.goodsPage;

import Controller.GoodController;
import Model.Category;
import Model.Good;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.EventListener;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GoodPageController implements Initializable {

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
        TreeItem<String> categories = new TreeItem<>("Categories");
        categories.setExpanded(true);
        categories.getChildren().add(Category.rootCategory.getCategory());

        categoriesTreeView.setRoot(categories);
        categoriesTreeView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(categoriesTreeView.getSelectionModel().getSelectedItem() != null) {
                    FiltersVBox.getChildren().clear();
                    String selectedCategory = categoriesTreeView.getSelectionModel().getSelectedItem().getValue();
                    Label filters = new Label("filters");
                    Label filterByCategoryProperties = new Label("Category Properties");
                    Label filterByGeneralProperties = new Label("General Properties");

                    FiltersVBox.getChildren().add(filterByCategoryProperties);
                    for (String property : Category.getCategoryByName(selectedCategory).getSpecialProperties()) {
                        FiltersVBox.getChildren().add(FilterItemFactory.createFilterItem(property, get()));
                    }
                    FiltersVBox.getChildren().add(filterByGeneralProperties);
                    for (String property : Category.getGeneralProperties()) {
                        FiltersVBox.getChildren().add(FilterItemFactory.createFilterItem(property, get()));
                    }
                }
            }
        });
        updateGoods();


    }

    public GoodPageController get() {
        return this;
    }


    public void updateGoods() {
        firstColumnGoods.setSpacing(10);
        secondColumnGood.setSpacing(10);
        firstColumnGoods.getChildren().clear();
        secondColumnGood.getChildren().clear();
        for (int i = 0; i < GoodController.getSelectedGoods().size(); i++) {
            if (i % 2 == 0) {
                firstColumnGoods.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            } else {
                secondColumnGood.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            }
        }
    }

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
