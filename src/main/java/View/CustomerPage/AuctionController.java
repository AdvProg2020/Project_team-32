package View.CustomerPage;

import Server.Model.Auction;
import Server.Model.Message;
import View.Client;
import View.CustomerPage.Auction.AuctionShape;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AuctionController implements Initializable {
    @FXML
    private Pane auctionPane;

    @FXML
    private VBox auctionBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get allAuctions", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            ArrayList<Auction> allAuctions = (ArrayList<Auction>) message.get("allAuctions");
            for (Auction allAuction : allAuctions) {
                String sellerName = allAuction.getSeller().getUserName();
                String goodID =allAuction.getGood().getGoodID();
                String ID = allAuction.getID();
                AuctionShape auctionShape = new AuctionShape(sellerName,goodID,ID);
                auctionBox.getChildren().add(auctionShape);
                auctionShape.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                    }
                });
            }
        }

    }
}
