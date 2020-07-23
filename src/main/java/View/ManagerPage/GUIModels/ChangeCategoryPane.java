package View.ManagerPage.GUIModels;

import Server.Controller.CategoryController;
import Server.Controller.Exeptions.CategoryNotFindException;
import Server.Controller.Exeptions.DuplicateCategoryException;
import Server.Model.Category;
import View.Client;
import View.ManagerPage.AddEditCategoryPageController;
import View.ManagerPage.CategoryManagerController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeCategoryPane extends Pane {

    CategoryManagerController parentController;
    AddEditCategoryPageController controller;
    boolean isEditPane;
    Category categoryToChange;

    public ChangeCategoryPane(CategoryManagerController parentController, Category categoryToChange) {

        URL url = null;
        try {
            url = new File("src\\main\\resources\\GUIFiles\\add-edit-category-page.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(url);

        //set main scene
        try {
            Parent parent = loader.load();
            this.controller = loader.getController();
            getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set controllers
        this.parentController = parentController;

        //set fields
        this.categoryToChange = categoryToChange;
        isEditPane = categoryToChange != null;

        //check if category is not null
        if (isEditPane) {
            setCategoryAmount(categoryToChange);
        }

        //set controller filed
        controller.setCategoryPane(this);

    }

    private void setCategoryAmount(Category categoryToChange) {

        controller.nameInput.setText(categoryToChange.getName());

        controller.parentNameInput.setText(categoryToChange.getParentCategoryName());
        controller.parentNameInput.setDisable(true);

        controller.setVBoxContent(categoryToChange.getSpecialProperties());

    }

    public void makeCategory(String name, String parentName, ArrayList<String> properties) {

        if (!isEditPane) {
            addCategory(name, properties, parentName);
        } else {
            editCategory(categoryToChange, name, properties);
        }

        parentController.closeStage();
        parentController.updateTable();

    }

    private void editCategory(Category categoryToChange, String name, ArrayList<String> properties) {
        HashMap<String , Object> inputs = new HashMap<>();
        inputs.put("name", name);
        inputs.put("properties",properties);
        inputs.put("curName", categoryToChange.getName());
        Client.sendMessage("edit category", inputs);
        String serverAnswer = (String) Client.getMessage().get("status");
        if(serverAnswer.equals("category not find")){
            new Alert(Alert.AlertType.WARNING, "Category Not Find.").show();
        }
    }

    private void addCategory(String name, ArrayList<String> properties, String parentName) {
        HashMap<String , Object> inputs = new HashMap<>();
        inputs.put("name", name);
        inputs.put("properties",properties);
        inputs.put("parentName", parentName);
        Client.sendMessage("add category",inputs);
        String serverAnswer = (String) Client.getMessage().get("status");
        if(serverAnswer.equals("category not find")){
            new Alert(Alert.AlertType.WARNING, "Category Not Find.").show();
        }
        else if(serverAnswer.equals("duplicate category")){
            new Alert(Alert.AlertType.WARNING, "Duplicate Category").show();
        }
    }
}
