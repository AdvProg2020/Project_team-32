package View.CustomerPage;

import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Controller.CustomerController;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Model.BuyLog;
import Server.Model.Customer;
import Server.Model.Message;
import View.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {

    public Label address;
    public Label status;
    public Label phoneNumber;
    @FXML
    private Button orderShowPageButton;

    @FXML
    private Label result_Label;

    @FXML
    private TextField IdTextField;

    @FXML
    private Button ratePageButton;

    @FXML
    private TableView<BuyLog> table;

    @FXML
    private TableColumn<BuyLog, String> logCol;

    @FXML
    private Button rateButton;

    @FXML
    private Label rateResult;

    @FXML
    private TextField rateTextFied;

    @FXML
    private VBox orderVBox;

    @FXML
    private Label LogID_VBox;

    @FXML
    private Label date_VBox;

    @FXML
    private Label goodName_VBox;

    @FXML
    private Label seller_VBox;

    @FXML
    private Label price_VBox;
    @FXML
    private VBox rateVbox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fixSounds();

        orderVBox.setVisible(false);
        rateVbox.setVisible(false);
        logCol.setCellValueFactory(new PropertyValueFactory<>("LogID"));
        ObservableList<BuyLog> logList = FXCollections.observableArrayList();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get buylogs", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            for (BuyLog buyLog : (ArrayList<BuyLog>)message.get("buyLogs")) {
                logList.add(buyLog);
            }
            table.setItems(logList);
        }

        ratePageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                table.setVisible(false);
                rateVbox.setVisible(true);
                rateButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        HashMap<String,Object> input = new HashMap<>();
                        input.put("IDrate",IdTextField.getText().trim());
                        input.put("pointRate",Integer.parseInt(rateTextFied.getText().trim()));
                        Client.sendMessage("rate", input);
                        Message message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            new Alert(Alert.AlertType.CONFIRMATION).show();
                        }else if (message.get("status").equals("RateException")){
                            Alert alert =new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("RateException");
                            alert.show();                        }
                    }
                });
            }
        });
        orderShowPageButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //                    BuyLog log =CustomerController.getBugLogWithId(IdTextField.getText().trim(),((Customer)AccountController.loggedInUser));
                HashMap<String,Object> input = new HashMap<>();
                input.put("ID",IdTextField.getText().trim());
                Client.sendMessage("get individual buylog", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    BuyLog log = (BuyLog) message.get("buylog");
                    table.setVisible(false);
                    orderVBox.setVisible(true);
                    LogID_VBox.setText("log ID :"+log.getLogID());
                    date_VBox.setText("date: "+log.getDate().toString());
                    goodName_VBox.setText("good's Name: "+log.getGoodsBought().getName());
                    seller_VBox.setText("seller name: "+log.getSellerUserName());
                    price_VBox.setText("price paid: "+log.getPricePaid());
                    status.setText("delivery status: "+log.getDeliveryStatus().name());
                    address.setText("address : "+log.getAddress());
                    phoneNumber.setText("phone number: "+log.getPhoneNumber());
                }else if (message.get("status").equals("InvalidIDException")){
                    Alert alert =new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("InvalidIDException");
                    alert.show();
                }

            }
        });
    }

    private void fixSounds() {
        orderShowPageButton.setOnMouseClicked(event -> Controller.sound(3));
        rateButton.setOnMouseClicked(event -> Controller.sound(3));
        ratePageButton.setOnMouseClicked(event -> Controller.sound(3));

        orderShowPageButton.setOnMouseEntered(event -> Controller.sound(1));
        rateButton.setOnMouseEntered(event -> Controller.sound(1));
        ratePageButton.setOnMouseEntered(event -> Controller.sound(1));

        price_VBox.setOnMouseEntered(event -> Controller.sound(2));
        seller_VBox.setOnMouseEntered(event -> Controller.sound(2));
        goodName_VBox.setOnMouseEntered(event -> Controller.sound(2));
        date_VBox.setOnMouseEntered(event -> Controller.sound(2));
        LogID_VBox.setOnMouseEntered(event -> Controller.sound(2));
        rateResult.setOnMouseEntered(event -> Controller.sound(2));
        result_Label.setOnMouseEntered(event -> Controller.sound(2));
    }
}
