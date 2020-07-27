package View.ManagerPage.GUIModels;

import Server.Model.BuyLog;
import View.ManagerPage.ShowBuyLogController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BuyLogCard extends Pane {

    ShowBuyLogController controller;
    BuyLog buyLog;

    public BuyLogCard(BuyLog buyLog) {

        this.buyLog = buyLog;

        //load FXML File and add Controller
        URL url = null;
        try {
            url = new File("src/main/resources/GUIFiles/ManagerMenu/show-buy-log.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(url);

        //set main scene
        try {
            Parent parent = loader.load();
            this.controller = loader.getController();
            getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Initial Page Fields
        controller.address.setText(buyLog.getAddress());
        controller.date.setText(buyLog.getDate().toString());
        controller.phoneNumber.setText(buyLog.getPhoneNumber());
        controller.price.setText("" + buyLog.getPricePaid());
        if(buyLog.getGoodsBought() != null) {
            controller.productId.setText(buyLog.getGoodsBought().getName());
        }
        controller.sellerUserName.setText(buyLog.getSellerUserName());

    }
}
