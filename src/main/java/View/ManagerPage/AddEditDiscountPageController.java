package View.ManagerPage;


import Server.Controller.AccountController;
import Server.Model.Customer;
import Server.Model.Message;
import Server.Model.Person;
import View.Client;
import View.ManagerPage.GUIModels.ChangeDiscountPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AddEditDiscountPageController implements Initializable {

    public Slider percentAmountPicker;
    public Label percentAmountLabel;
    public VBox userPicker;
    public TextField maxAmountInput;
    public TextField idInput;
    public DatePicker datePicker;
    public TextField numberOfUseInput;
    private ChangeDiscountPane changeDiscountPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        percentAmountPicker.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                percentAmountLabel.setText("" + newValue.intValue() + "%");
            }
        });

        setVBoxContent();

    }


    private void setVBoxContent(){


        Client.sendMessage("get all customers", new HashMap<>());
        Message serverAnswer = Client.getMessage();
        ArrayList<Customer> user = (ArrayList<Customer>) serverAnswer.get("all customers");

        for (Customer customer : user) {
            CheckBox checkBox = new CheckBox(customer.getUserName());
            checkBox.setPadding(new Insets(10,10,10,5));
            userPicker.getChildren().add(checkBox);
        }

    }

    private ArrayList<String> getVBoxContent(){

        ArrayList<String> customers = new ArrayList<>();

        for (Node child : userPicker.getChildren()) {
            try {
                if(child instanceof CheckBox && ((CheckBox) child).isSelected()){
                    customers.add(((CheckBox) child).getText());
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }

        return customers;

    }


    public void submitChanges(ActionEvent actionEvent) {
        changeDiscountPane.makeDiscount(datePicker.getValue(),idInput.getText(),maxAmountInput.getText(),percentAmountPicker.getValue(),getVBoxContent(),numberOfUseInput.getText());
    }

    public void setChangeDiscountPane(ChangeDiscountPane changeDiscountPane) {
        this.changeDiscountPane = changeDiscountPane;
    }

}
