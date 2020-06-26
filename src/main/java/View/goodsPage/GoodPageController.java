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
    private TreeView filterTreeView;

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
                String selectedCategory = categoriesTreeView.getSelectionModel().getSelectedItem().getValue();
                TreeItem<String > filters = new CheckBoxTreeItem<>("Filters");
                filterTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
                TreeItem<String > filterByCategoryProperties = new CheckBoxTreeItem<>("Category Properties");
                TreeItem<String> filterByGeneralProperties = new CheckBoxTreeItem<>("General Properties");
                filterTreeView.setRoot(filters);
                filters.setExpanded(true);
                //filterTreeView.setEditable(true);
                filters.getChildren().addAll(filterByCategoryProperties, filterByGeneralProperties);
                for (String property : Category.getCategoryByName(selectedCategory).getSpecialProperties()) {
                    CheckBoxTreeItem<String> a = new CheckBoxTreeItem<String>(property);
                    a.addEventHandler( MouseEvent.MOUSE_CLICKED, new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            if (a.isSelected()) {
                                try {
                                    GoodController.filter(a.getValue(), valueOfFileter());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                try {
                                    GoodController.disableFilter(a.getValue());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            updateGoods();
                        }
                    });
                    filterByCategoryProperties.getChildren().add(a);
                }
                for (String property : Category.getGeneralProperties()) {
                    filterByGeneralProperties.getChildren().add(new CheckBoxTreeItem<String>(property));
                }
            }
        });



    }

    public void updateGoods() {
        firstColumnGoods.setSpacing(10);
        secondColumnGood.setSpacing(10);
        GoodController.setCurrentCategory(Category.getCategoryByName(categoriesTreeView.getSelectionModel().getSelectedItem().getValue()));
        for (int i = 0; i < GoodController.getSelectedGoods().size(); i++) {
            String name = Good.getAllGoods().get(i).getName();
            String imageAddress = Good.getAllGoods().get(i).getImageAddress();
            float point = Good.getAllGoods().get(i).getPoint();
            try {
                if (i % 2 == 0) {
                    firstColumnGoods.getChildren().add(GoodIconFactory.createIcon(name, imageAddress, point));
                } else {
                    secondColumnGood.getChildren().add(GoodIconFactory.createIcon(name, imageAddress, point));
                }
            } catch (Exception e) {

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
