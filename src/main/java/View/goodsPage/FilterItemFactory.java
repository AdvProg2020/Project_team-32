package View.goodsPage;
import Server.Controller.Filterable;
import View.Updatable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class FilterItemFactory {

    public static HBox createFilterItem(String filter, Updatable controller, Filterable filterable) {
        HBox filterItem = new HBox();
        filterItem.setAlignment(Pos.CENTER);
        filterItem.setSpacing(10);
        Label filterLable = new Label(filter);
        CheckBox checkBox = new CheckBox();
        checkBox.setOnAction(e -> {
            if(checkBox.isSelected()) {
                try {
                    filterable.filter(filterLable.getText(), controller.valueOfFileter());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else {
                try {
                    System.out.println("----------------- fileter for disabling ----------------------");
                    System.out.println(filterLable.getText());
                    filterable.disableFilter(filterLable.getText());
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
