package View.ManagerPage;


import Controller.AccountController;
import Model.Customer;
import Model.Person;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddEditDiscountPageController implements Initializable {

    public Slider percentAmountPicker;
    public Label percentAmountLabel;
    public VBox userPicker;
    public TextField maxAmountInput;
    public TextField idInput;
    public DatePicker datePicker;


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

        ArrayList<Customer> user = AccountController.getAllCustomer();

        System.out.println(user);

        for (Customer customer : user) {
            CheckBox checkBox = new CheckBox(customer.getUserName());
            checkBox.setPadding(new Insets(10,10,10,5));
            userPicker.getChildren().add(checkBox);
        }

    }


}
