package View.CustomerPage;

import Server.Controller.Controller;
import Server.Model.Message;
import View.Client;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sun.tools.jar.CommandLine;

import java.net.URL;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class PurchaseControllerFXML implements Initializable {
    public Button nextButton;
    public Accordion accordion;
    public TextField phoneNumberText;
    public Label discountCheckLabel;
    public TextField addressText;
    public ImageView discountImage;
    public AnchorPane NoDisPane;
    public Button noDisPay;
    public Label noDisResult;
    public Button checkDiscount;
    public TextField discountTextField;
    public VBox disVBox;
    public Label disResult;
    public Button disPay;
    public CheckBox bankPayCheckBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fixSounds();
        mySetVisible();
        nextButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (!phoneNumberText.getText().isEmpty() && !addressText.getText().isEmpty()) {
                    String phoneNumber = phoneNumberText.getText();
                    String address = addressText.getText();
                    bankPayCheckBox.setVisible(true);
                    accordion.setVisible(true);
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get shoppingBasket price", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        final float[] totalPrice = {(float) message.get("price")};
                        purchse(totalPrice, phoneNumber, address);
                    }
                }
            }
        });

    }

    private void mySetVisible() {
        bankPayCheckBox.setVisible(false);
        disVBox.setVisible(false);
        accordion.setVisible(false);
    }

    private void purchse(float[] totalPrice, String phoneNumber, String address) {
        int random = ((new Random()).nextInt(100));
        final float[] discountPercent = {0};
        noDisPay.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (totalPrice[0] >= 1000)
                    chancePercent(discountPercent, totalPrice);
                else if ((random % 10) == 0)
                    randomPercent(discountPercent, random, totalPrice);

                if (bankPayCheckBox.isSelected()) {
                    String result= bankServer(totalPrice);
                    if (result.equals("done successfully")){
                        HashMap<String, Object> input = new HashMap<>();
                        input.put("type","bank");
                        input.put("address",address);
                        input.put("phoneNumber",phoneNumber);
                        input.put("price",totalPrice[0]);
                        input.put("discount",discountPercent[0]);
                        Client.sendMessage("pay", input);
                        Message message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            showConfirm("you have successfully bought this thing");
                            noDisResult.setText("you have succesfully bought things");
                        }
                    }
                    //todo when buying is successful
                } else {
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("type","wallet");
                    input.put("address",address);
                    input.put("phoneNumber",phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }
                }
            }
        });
        checkDiscount.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                HashMap<String, Object> input = new HashMap<>();
                input.put("id", discountTextField.getText().trim());
                Client.sendMessage("get discount percent", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    checkDiscount(message, discountPercent, totalPrice,address,phoneNumber);
                } else if (message.get("status").equals("DiscountNotUsableException")) {
                    showError(Alert.AlertType.ERROR, "DiscountNotUsableException");
                } else if (message.get("status").equals("InvalidIDException")) {
                    showError(Alert.AlertType.ERROR, "InvalidIDException");
                }
            }
        });
    }

    private String bankServer(float[] totalPrice) {
        String result1 = handleAccountID();
        if (result1 != null) return result1;
        Client.bankServer.sendMessageToBank("get_token "+Client.user.getUserName()+" "+Client.user.getPassWord());
        String result = Client.bankServer.getMessageFromBank();
        String[] temp = result.split(" ");
        if (temp.length==1){
            Client.bankServer.setServerToken(result);
            result = Client.bankServer.getServerToken()+" "+"move "+totalPrice[0]+" "+
                    Client.bankServer.getAccountID()+" "+"1 "+" boleshit";
            Client.bankServer.sendMessageToBank(result);
            String recipt = Client.bankServer.getMessageFromBank();
            temp =recipt.split(" ");
            if (temp.length ==1) {
                Client.bankServer.sendMessageToBank("pay " + recipt);
                String output = Client.bankServer.getMessageFromBank();
                if (output.equals("done successfully")) {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("payed by bank macount succcessfully");
                    alert.show();
                    return "done successfully";
                } else if (output.equals("source account does not have enough money")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("source account does not have enough money");
                    alert.show();
                } else {
                    System.out.println(output);
                    return output;
                }
            }else {
                System.out.println(recipt);
                return recipt;
            }
        }else {
            System.out.println(result);
            return result;
        }
        return "aaaa";
    }

    private static String handleAccountID() {
        if (Client.bankServer.getAccountID() == -1){
            String message ="create_account "+Client.user.getFirstName()+" "+Client.user.getLastName()
                    +" "+Client.user.getUserName()+" "+Client.user.getPassWord()+" "+Client.user.getPassWord();
            Client.bankServer.sendMessageToBank(message);
            String result =Client.bankServer.getMessageFromBank();
            String[] temp = result.split(" ");
            if (temp.length==1){
                Client.bankServer.setAccountID(Integer.parseInt(result));
            }
            else {
                System.out.println(result);
                return result;
            }

        }
        return null;
    }


    private void randomPercent(float[] discountPercent, int random, float[] totalPrice) {
        discountPercent[0] = (random % 20);
        HashMap<String, Object> input = new HashMap<>();
        input.put("price", totalPrice[0]);
        input.put("discount", discountPercent);
        Client.sendMessage("get discounted price", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            totalPrice[0] = (float) message.get("discounted price");
            showConfirm("you are lucky : you got " + discountPercent[0] + "percent discount");
        }
    }

    private void chancePercent(float[] discountPercent, float[] totalPrice) {
        discountPercent[0] = 10;
        HashMap<String, Object> input = new HashMap<>();
        input.put("price", totalPrice[0]);
        input.put("discount", discountPercent);
        Client.sendMessage("get discounted price", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            totalPrice[0] = (float) message.get("discounted price");
            showConfirm("you get 10% discount because you bought over 1000000 ");
        }
    }

    private void checkDiscount(Message message, float[] discountPercent, float[] totalPrice, String address, String phone) {
        HashMap<String, Object> input;
        discountPercent[0] = (float) message.get("discount percent");
        input = new HashMap<>();
        input.put("price", totalPrice[0]);
        input.put("discount", discountPercent);
        Client.sendMessage("get discounted price", input);
        message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            totalPrice[0] = (float) message.get("discounted price");
            disVBox.setVisible(true);
            EventHandler eventHandler = makeEventHandler(discountPercent, totalPrice ,address,phone);
            disPay.setOnMouseClicked(eventHandler);
        }
    }

    private EventHandler makeEventHandler(float[] discountPercent, float[] totalPrice, String address,String phoneNumber) {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                if (bankPayCheckBox.isSelected()) {
                    String result = bankServer(totalPrice);
                    if (result.equals("done successfully")){
                        HashMap<String, Object> input = new HashMap<>();
                        input.put("type","bank");
                        input.put("address",address);
                        input.put("phoneNumber",phoneNumber);
                        input.put("price",totalPrice[0]);
                        input.put("discount",discountPercent[0]);
                        Client.sendMessage("pay", input);
                        Message message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            showConfirm("you have successfully bought this thing");
                            noDisResult.setText("you have succesfully bought things");
                        }
                    }
                    //todo
                } else {
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("type","wallet");
                    input.put("address",address);
                    input.put("phoneNumber",phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }
                }
            }
        };
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
