package View.ManagerPage;

import Server.Controller.CategoryController;
import Server.Model.Category;
import Server.Model.Message;
import Server.Server;
import View.Client;
import View.ManagerPage.GUIModels.ChangeCategoryPane;
import com.sun.org.apache.xpath.internal.objects.XObject;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
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
        categoryTable.getItems().addAll(getAllCategories());
    }

    private Collection<? extends Category> getAllCategories() {
        Client.sendMessage("get all categories", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        return (Collection<? extends Category>) serverAnswer.get("all categories");
    }


    public void remove(ActionEvent actionEvent) {
        if(categoryTable.getSelectionModel().getSelectedItem() != null){
            HashMap<String , Object> inputs = new HashMap<>();
            inputs.put("category name", categoryTable.getSelectionModel().getSelectedItem().getName());
            Client.sendMessage("remove category",inputs);
            Message serverAnswer = Client.getMessage();
            if(serverAnswer.get("status").equals("category not find")){
                new Alert(Alert.AlertType.WARNING,"Category not find.").show();
            }
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
