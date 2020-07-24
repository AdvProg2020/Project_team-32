package View.CustomerPage;

import Server.Model.Auction;
import Server.Model.Message;
import View.Client;
import View.CustomerPage.Auction.AuctionShape;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

public class AuctionController implements Initializable {
    private  ArrayList<AuctionShape> shapeArrayList = new ArrayList<>();
    @FXML
    private Pane auctionPane;

    @FXML
    private VBox auctionBox;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get allAuctions", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            ArrayList<Auction> allAuctions = (ArrayList<Auction>) message.get("allAuctions");
            for (Auction allAuction : allAuctions) {
                String sellerName = allAuction.getSeller().getUserName();
                String goodID =allAuction.getGood().getGoodID();
                String ID = allAuction.getID();
                Date date = allAuction.getExpire();
                AuctionShape auctionShape = new AuctionShape(sellerName,goodID,ID,date);
                shapeArrayList.add(auctionShape);
                auctionBox.getChildren().add(auctionShape);
                auctionShape.setOnMouseClicked(new javafx.event.EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        URL url = null;
                        try {
                            url = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\SingleAuctionPage.fxml").toURI().toURL();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        FXMLLoader loader = new FXMLLoader(url);
                        Parent parent = null;
                        try {
                            parent = loader.load();
                            auctionPane.getChildren().clear();
                            auctionPane.getChildren().add(parent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }

    }
}
