package View.ManagerPage;

import View.ManagerPage.GUIModels.ChangeCategoryPane;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEditCategoryPageController implements Initializable {


    public TextField nameInput;
    public TextField parentNameInput;
    public TextField propertyInput;
    public VBox propertyList;
    private ChangeCategoryPane categoryPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void submitChanges(ActionEvent actionEvent) {
        categoryPane.makeCategory(nameInput.getText(), parentNameInput.getText(), getProperty());
    }


    private ArrayList<String > getProperty(){

        ArrayList<String > properties = new ArrayList<>();

        for (Node child : propertyList.getChildren()) {
            if(child instanceof Label){
                properties.add(((Label) child).getText());
            }
        }

        return properties;

    }


    public void addProperty(ActionEvent actionEvent) {
        if(!propertyInput.getText().equals("")){

            Label label = new Label(propertyInput.getText());
            label.setPadding(new Insets(10,10,10,10));

            propertyList.getChildren().add(label);

            propertyInput.setText("");

        }
    }

    public void setVBoxContent(ArrayList<String> specialProperties) {

        for (String property : specialProperties) {

            Label label = new Label(property);
            label.setPadding(new Insets(10,10,10,10));

            propertyList.getChildren().add(label);

        }

    }

    public void setCategoryPane(ChangeCategoryPane categoryPane) {
        this.categoryPane = categoryPane;
    }
}
