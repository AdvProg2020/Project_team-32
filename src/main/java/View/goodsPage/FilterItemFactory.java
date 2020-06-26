package View.goodsPage;

import Controller.GoodController;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class FilterItemFactory {

    public static HBox createFilterItem(String filter, GoodPageController controller) {
        HBox filterItem = new HBox();
        Label filterLable = new Label(filter);
        CheckBox checkBox = new CheckBox();
        checkBox.setOnAction(e -> {
            if(checkBox.isSelected()) {
                try {
                    GoodController.filter(filterLable.getText(), controller.valueOfFileter());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                try {
                    GoodController.disableFilter(filterLable.getText());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            controller.updateGoods();
        });
        filterItem.getChildren().addAll(filterLable,  checkBox);
        return filterItem;
    }

}
