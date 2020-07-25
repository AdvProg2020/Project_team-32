package View.SellerPage;

import Server.Model.Auction;
import Server.Model.Good;
import Server.Model.Message;
import Server.Model.Seller;
import View.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AuctionController implements Initializable {
    @FXML
    private TextField auctionText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private Button confirmAuction;

    @FXML
    private TableView auctionTable;

    @FXML
    private TableColumn<String, Good> nameCulomn;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nameCulomn.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get seller product list", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            auctionTable.getItems().addAll((ArrayList<Good>) message.get("product list"));
        }
        confirmAuction.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String goodID =auctionText.getText().trim();
                HashMap<String, Object> input = new HashMap<>();
                input.put("date",datePicker.getValue());
                input.put("goodID",goodID);
                Client.sendMessage("set auction", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    new Alert(Alert.AlertType.CONFIRMATION).show();
                }else  if (message.get("status").equals("MultipleAuctionException")){
                    new Alert(Alert.AlertType.ERROR).show();
                }
            }
        });

    }
}
