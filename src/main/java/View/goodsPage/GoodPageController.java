package View.goodsPage;

import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
        //final Node categoryIcon = new ImageView(new Image(getClass().getResourceAsStream("GUIFiles\\GoodsMenuIcons\\categoryIcon1.png")));
        //TreeItem<String> categories = new TreeItem<>("Categories", categoryIcon);

        // initializing categories
        TreeItem<String> categories = new TreeItem<>("Categories");
        categories.setExpanded(true);
        TreeItem<String> laptops = new TreeItem<>("laptops");
        TreeItem<String> TVs = new TreeItem<>("TVs");
        categories.getChildren().addAll(laptops, TVs);
        categoriesTreeView.setRoot(categories);

        // initializing filters
        TreeItem<String > filters = new CheckBoxTreeItem<>("Filters");
        filterTreeView.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        TreeItem<String > filterByCategoryProperties = new CheckBoxTreeItem<>("Category Properties");
        TreeItem<String> filterByGeneralProperties = new CheckBoxTreeItem<>("General Properties");
        filterTreeView.setRoot(filters);
        filters.setExpanded(true);
        filterTreeView.setEditable(true);
        filters.getChildren().addAll(filterByCategoryProperties, filterByGeneralProperties);
        CheckBoxTreeItem<String> a = new CheckBoxTreeItem<>("hello");
        filterByCategoryProperties.getChildren().addAll(a);
        CheckBoxTreeItem<String> b = new CheckBoxTreeItem<>("hi");
        filterByCategoryProperties.getChildren().addAll(b);



        // initializing columns of goods
        firstColumnGoods.setSpacing(10);
        secondColumnGood.setSpacing(10);
        try {
            Pane pane = GoodIconFactory.createIcon("hello","src\\main\\resources\\GUIFiles\\apple.jpg",4);
            firstColumnGoods.getChildren().add(pane);
            Pane banana = GoodIconFactory.createIcon("banana","src\\main\\resources\\GUIFiles\\banana.jpg", 6);
            firstColumnGoods.getChildren().add(banana);
            Pane fish = GoodIconFactory.createIcon("fish", "src\\main\\resources\\GUIFiles\\fish.jpg", 3);
            firstColumnGoods.getChildren().add(fish);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
