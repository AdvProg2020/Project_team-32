package View.ManagerPage;

import Controller.CategoryController;
import Controller.GoodController;
import Model.Category;
import Model.Discount;
import View.ManagerPage.GUIModels.ChangeCategoryPane;
import View.ManagerPage.GUIModels.ChangeDiscountPane;
import com.sun.deploy.panel.PropertyTreeModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sun.awt.geom.AreaOp;

import java.net.URL;
import java.util.ResourceBundle;

public class CategoryManagerController implements Initializable {

    public TableView<Category> categoryTable;
    public TableColumn<Category, String> parentCategory;
    public TableColumn<Category, String> nameColumn;
    private Stage stageToShow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set column value
        parentCategory.setCellValueFactory(new PropertyValueFactory<>("parentCategoryName"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //initial table items
        updateTable();

        stageToShow = new Stage();

    }

    public void updateTable() {
        categoryTable.getItems().clear();
        categoryTable.getItems().addAll(Category.getAllCategories());
    }



    public void remove(ActionEvent actionEvent) {
        if(categoryTable.getSelectionModel().getSelectedItem() != null){
            CategoryController.removeCategory(categoryTable.getSelectionModel().getSelectedItem());
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a category.").show();
        }
    }

    public void addCategory(ActionEvent actionEvent) {
        stageToShow.setScene(new Scene(new ChangeCategoryPane(this, null)));
        stageToShow.show();
    }

    public void editCategory(ActionEvent actionEvent) {
        if (categoryTable.getSelectionModel().getSelectedItem() != null) {
            Category category = categoryTable.getSelectionModel().getSelectedItem();
            stageToShow.setScene(new Scene(new ChangeCategoryPane(this, category)));
            stageToShow.show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Should select a category.").show();
        }
    }

    public void closeStage() {
        stageToShow.close();
    }
}
