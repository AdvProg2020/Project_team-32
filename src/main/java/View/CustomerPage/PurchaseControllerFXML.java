package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Controller.Exeptions.DiscountNotUsableException;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.PurchaseController;
import Server.Model.Customer;
import Server.Model.Good;
import Server.Model.Message;
import View.Client;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class PurchaseControllerFXML implements Initializable {
    public Button nextButton;
    public Accordion accordion;
    @FXML
    private TextField phoneNumberText;

    @FXML
    private Label discountCheckLabel;
    @FXML
    private TextField addressText;

    @FXML
    private ImageView discountImage;

    @FXML
    private AnchorPane NoDisPane;

    @FXML
    private Button noDisPay;

    @FXML
    private Label noDisResult;

    @FXML
    private Button checkDiscount;

    @FXML
    private TextField discountTextField;

    @FXML
    private VBox disVBox;

    @FXML
    private Label disResult;

    @FXML
    private Button disPay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fixSounds();

        disVBox.setVisible(false);
        accordion.setVisible(false);
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!phoneNumberText.getText().isEmpty() && !addressText.getText().isEmpty()){
                    String phoneNumber = phoneNumberText.getText();
                    String address = addressText.getText();
                    accordion.setVisible(true);
                    Customer customer = ((Customer)AccountController.loggedInUser);
                    final float[] discountPercent = {0};
                    int random = ((new Random()).nextInt(100));
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get shoppingBasket price", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        final float[] totalPrice = {(float) message.get("price")};
                        purchse(customer, discountPercent, random, totalPrice, noDisPay, noDisResult,
                                checkDiscount, discountTextField, disVBox, disPay, discountCheckLabel);
                    }
                }
            }
        });

    }

    private static void purchse(Customer customer, float[] discountPercent, int random, float[] totalPrice, Button noDisPay, Label noDisResult, Button checkDiscount, TextField discountTextField, VBox disVBox, Button disPay, Label discountCheckLabel) {
        noDisPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (customer.getCredit()>= totalPrice[0]){
                    if (totalPrice[0] >= 1000) {
                        discountPercent[0] = 10;
                        HashMap<String, Object> input = new HashMap<>();
                        input.put("price",totalPrice[0]);
                        input.put("discount",discountPercent);
                        Client.sendMessage("get discounted price", input);
                        Message message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            totalPrice[0] = (float) message.get("discounted price");
                            showConfirm("you get 10% discount because you bought over 1000000 ");
                        }

                    } else if ((random % 10) == 0) {
                        discountPercent[0] = (random % 20);
                        HashMap<String, Object> input = new HashMap<>();
                        input.put("price",totalPrice[0]);
                        input.put("discount",discountPercent);
                        Client.sendMessage("get discounted price", input);
                        Message message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            totalPrice[0] = (float) message.get("discounted price");
                            showConfirm("you are lucky : you got " + discountPercent[0] + "percent discount");
                        }
                    }
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("price",totalPrice[0]);
                    input.put("discount",discountPercent);
                    Client.sendMessage("pay by wallet", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }
//                    PurchaseController.payCommand(customer, totalPrice[0], discountPercent[0]);
                    //pop up should be shown here
                }
                else {
                    noDisResult.setText("you don't have enaugh moneey");
                }
            }
        });
        checkDiscount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HashMap<String, Object> input = new HashMap<>();
                input.put("id",discountTextField.getText().trim());
                Client.sendMessage("get discount percent", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    discountPercent[0] = (float) message.get("discount percent");
                    input = new HashMap<>();
                    input.put("price",totalPrice[0]);
                    input.put("discount",discountPercent);
                    Client.sendMessage("get discounted price", input);
                    message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        totalPrice[0] = (float) message.get("discounted price");
                        disVBox.setVisible(true);
                        disPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                if (customer.getCredit()>= totalPrice[0]){
                                    HashMap<String, Object> input = new HashMap<>();
                                    input.put("price",totalPrice[0]);
                                    input.put("discount",discountPercent);
                                    Client.sendMessage("pay by wallet", input);
                                    Message message = Client.getMessage();
                                    if (message.get("status").equals("successful")) {
                                        showConfirm("you have successfully bought this thing");
                                        noDisResult.setText("you have succesfully bought things");
                                    }
                                }
                            }
                        });
                    }
                }
                else if (message.get("status").equals("DiscountNotUsableException")){
                    showError(Alert.AlertType.ERROR, "DiscountNotUsableException");
                }
                else if (message.get("status").equals("InvalidIDException")){
                    showError(Alert.AlertType.ERROR,"InvalidIDException");
                }


            }
        });
    }

    private static void showError(Alert.AlertType error, String discountNotUsableException) {
        Alert alert = new Alert(error);
        alert.setContentText(discountNotUsableException);
        alert.show();
    }

    private static void showConfirm(String vla) {
        showError(Alert.AlertType.INFORMATION, vla);
    }

    private void fixSounds() {
        nextButton.setOnMouseEntered(event -> Controller.sound(1));
        noDisPay.setOnMouseEntered(event -> Controller.sound(1));
        checkDiscount.setOnMouseEntered(event -> Controller.sound(1));
        disPay.setOnMouseEntered(event -> Controller.sound(1));

        nextButton.setOnMouseClicked(event -> Controller.sound(3));
        noDisPay.setOnMouseClicked(event -> Controller.sound(3));
        checkDiscount.setOnMouseClicked(event -> Controller.sound(3));
        disPay.setOnMouseClicked(event -> Controller.sound(3));

        discountCheckLabel.setOnMouseEntered(event -> Controller.sound(2));
        noDisResult.setOnMouseEntered(event -> Controller.sound(2));
        disResult.setOnMouseEntered(event -> Controller.sound(2));
    }
}
