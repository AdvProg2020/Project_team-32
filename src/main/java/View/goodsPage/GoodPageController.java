package View.goodsPage;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GoodPageController implements Initializable {

    @FXML
    private TreeView<String> treeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //final Node categoryIcon = new ImageView(new Image(getClass().getResourceAsStream("GUIFiles\\GoodsMenuIcons\\categoryIcon1.png")));
        //TreeItem<String> categories = new TreeItem<>("Categories", categoryIcon);
        TreeItem<String> categories = new TreeItem<>("Categories");
        categories.setExpanded(true);
        TreeItem<String> laptops = new TreeItem<>("laptops");
        TreeItem<String> TVs = new TreeItem<>("TVs");
        categories.getChildren().addAll(laptops, TVs);
        treeView.setRoot(categories);
    }



}
