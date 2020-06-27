package View.OffPageController;

import Controller.OffController;
import Model.Category;
import Model.Good;
import View.Updatable;
import View.goodsPage.FilterItemFactory;
import View.goodsPage.GoodIconFactory;
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
        categoriesTreeView1.setRoot(Category.rootCategory.getCategory());
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
                    for (String property : Category.getCategoryByName(selectedCategory).getSpecialProperties()) {
                        FiltersVBox1.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), OffController.getOffController()));
                    }
                    FiltersVBox1.getChildren().add(filterByGeneralProperties);
                    for (String property : Category.getGeneralProperties()) {
                        FiltersVBox1.getChildren().add(FilterItemFactory.createFilterItem(property, getController(), OffController.getOffController()));
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

        for (int i = 0; i < OffController.getOffController().getSelectedGoods().size(); i++) {
            if (i % 2 == 0) {
                firstColumnGoods1.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            } else {
                secondColumnGood1.getChildren().add(GoodIconFactory.createIcon(Good.getAllGoods().get(i)));
            }
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
