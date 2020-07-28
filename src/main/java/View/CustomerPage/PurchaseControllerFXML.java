package View.CustomerPage;

import Server.Controller.Controller;
import Server.Model.FileProduct;
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

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
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
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("transferType", "move");
                    input.put("type", "bank");
                    input.put("address", address);
                    input.put("phoneNumber", phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        handleFiles(message);
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }else if (message.get("status").equals("NotEnoughMoney")){
                        showError(Alert.AlertType.ERROR,"NotEnoughMoney");
                    }else if (message.get("status").equals("error")){
                        showError(Alert.AlertType.ERROR,"error in connecting to server");
                    }

                    //todo when buying is successful
                } else {
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("type", "wallet");
                    input.put("address", address);
                    input.put("phoneNumber", phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        handleFiles(message);
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }else if (message.get("status").equals("NotEnoughMoney")){
                        showError(Alert.AlertType.ERROR,"NotEnoughMoney");
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
                    checkDiscount(message, discountPercent, totalPrice, address, phoneNumber);
                } else if (message.get("status").equals("DiscountNotUsableException")) {
                    showError(Alert.AlertType.ERROR, "DiscountNotUsableException");
                } else if (message.get("status").equals("InvalidIDException")) {
                    showError(Alert.AlertType.ERROR, "InvalidIDException");
                }
            }
        });
    }

    private void handleFiles(Message message) {
        ArrayList<FileProduct> fileProducts =(ArrayList<FileProduct>)message.get("files");
        System.out.println("filllll;lesssss");
        System.out.println(message);
        if (fileProducts.size()!=0){
            for (int i = 0; i < fileProducts.size(); i++) {
                File file1 = new File("src\\main\\java\\Server\\Controller\\"+fileProducts.get(i).getGoodID());
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(fileProducts.get(i).getFile());
                    FileOutputStream fileOutputStream = new FileOutputStream(file1);
                    int b;
                    while  ((b=fileInputStream.read()) != -1){
                        fileOutputStream.write(b);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            showConfirm("you downloaded files automatically");
        }
    }


    private void randomPercent(float[] discountPercent, int random, float[] totalPrice) {
        discountPercent[0] = (random % 20);
        HashMap<String, Object> input = new HashMap<>();
        input.put("price", totalPrice[0]);
        input.put("discount", discountPercent[0]);
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
            EventHandler eventHandler = makeEventHandler(discountPercent, totalPrice, address, phone);
            disPay.setOnMouseClicked(eventHandler);
        }
    }

    private EventHandler makeEventHandler(float[] discountPercent, float[] totalPrice, String address, String phoneNumber) {
        return new EventHandler() {
            @Override
            public void handle(Event event) {
                if (bankPayCheckBox.isSelected()) {
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("transferType", "move");
                    input.put("type", "bank");
                    input.put("address", address);
                    input.put("phoneNumber", phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        handleFiles(message);
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }else if (message.get("status").equals("NotEnoughMoney")){
                        showError(Alert.AlertType.ERROR,"NotEnoughMoney");
                    }else if (message.get("status").equals("error")){
                        showError(Alert.AlertType.ERROR,"error in connecting to server");
                    }
                    //todo
                } else {
                    HashMap< String , Object > input = new HashMap<>();
                    input.put("type", "wallet");
                    input.put("address", address);
                    input.put("phoneNumber", phoneNumber);
                    input.put("price", totalPrice[0]);
                    input.put("discount", discountPercent[0]);
                    Client.sendMessage("pay", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        handleFiles(message);
                        showConfirm("you have successfully bought this thing");
                        noDisResult.setText("you have succesfully bought things");
                    }else if (message.get("status").equals("NotEnoughMoney")){
                        showError(Alert.AlertType.ERROR,"NotEnoughMoney");
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
