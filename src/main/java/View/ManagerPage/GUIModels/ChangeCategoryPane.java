package View.ManagerPage.GUIModels;

import Controller.CategoryController;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.DuplicateCategoryException;
import Model.Category;
import View.CustomerPage.CartController;
import View.ManagerPage.AddEditCategoryPageController;
import View.ManagerPage.CategoryManagerController;
import View.ManagerPage.DiscountManagerPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
            try {
                CategoryController.addCategory(name, properties, parentName);
            } catch (CategoryNotFindException e) {
                System.err.println("can't find parent");
            } catch (DuplicateCategoryException e) {
                System.err.println("duplicate name");
            }
        } else {
            CategoryController.editCategory(categoryToChange, name, properties);
        }

        parentController.closeStage();
        parentController.updateTable();

    }
}
