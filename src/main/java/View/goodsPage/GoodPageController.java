package View.goodsPage;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

public class GoodPageController implements Initializable {

    @FXML
    private TreeView<String> categoriesTreeView;

    @FXML
    private TreeView filterTreeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //final Node categoryIcon = new ImageView(new Image(getClass().getResourceAsStream("GUIFiles\\GoodsMenuIcons\\categoryIcon1.png")));
        //TreeItem<String> categories = new TreeItem<>("Categories", categoryIcon);
        TreeItem<String> categories = new TreeItem<>("Categories");
        categories.setExpanded(true);
        TreeItem<String> laptops = new TreeItem<>("laptops");
        TreeItem<String> TVs = new TreeItem<>("TVs");
        categories.getChildren().addAll(laptops, TVs);
        categoriesTreeView.setRoot(categories);

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



    }



}
