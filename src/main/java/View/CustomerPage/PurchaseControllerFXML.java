package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Controller.Exeptions.DiscountNotUsableException;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.PurchaseController;
import Server.Model.Customer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
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
                    final float[] totalPrice = {PurchaseController.calculatePrice(customer.getShoppingBaskets())};
                    noDisPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {

                            if (customer.getCredit()>= totalPrice[0]){
                                if (totalPrice[0] >= 1000) {
                                    discountPercent[0] = 10;
                                    totalPrice[0] = PurchaseController.getPriceDiscounted(totalPrice[0], discountPercent[0]);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("you get 10% discount because you bought over 1000000 ");
                                    alert.show();
                                } else if ((random % 10) == 0) {
                                    discountPercent[0] = (random % 20);
                                    totalPrice[0] = PurchaseController.getPriceDiscounted(totalPrice[0], discountPercent[0]);
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setContentText("you are lucky : you got " + discountPercent[0] + "percent discount");
                                    alert.show();
                                }
                                PurchaseController.payCommand(customer, totalPrice[0], discountPercent[0]);
                                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("result");
                                alert.setContentText("you have successfully bought this thing");
                                alert.show();
                                noDisResult.setText("you have succesfully bought things");
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
                            try {
                                discountPercent[0] = PurchaseController.getDiscountPercent(discountTextField.getText().trim(), customer);
                                totalPrice[0] = PurchaseController.getPriceDiscounted(totalPrice[0], discountPercent[0]);
                                disVBox.setVisible(true);
                                disPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (customer.getCredit()>= totalPrice[0]){
                                            PurchaseController.payCommand(customer, totalPrice[0], discountPercent[0]);
                                            Alert alert =new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("result");
                                            alert.setContentText("you have successfully bought this thing");
                                            alert.show();
                                        }
                                    }
                                });
                            } catch (InvalidIDException e) {
                                discountCheckLabel.setText("invalid ID");
                                System.out.println("what invalid ID");
                            } catch (DiscountNotUsableException e) {
                                discountCheckLabel.setText("you cant use this discount anymore");
                                System.out.println("dlnsddvnmv");
                            }
                        }
                    });

                }
            }
        });

    }
}
