package View.ChatPage.GUIModels;

import Server.Model.Customer;
import View.ChatPage.StartChatCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomerStatusCard extends Pane {

    private StartChatCardController controller;
    private Customer customer;

    public CustomerStatusCard(Customer customer) {

        this.customer = customer;

        URL url = null;
        try {
            url = new File("src/main/resources/GUIFiles/ChatMenu/start-chat-card.fxml").toURI().toURL();
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

        initialPaneFields();

    }

    private void initialPaneFields() {
        controller.userImage = new ImageView(String.valueOf(customer.getImageUrl()));
        //online status
        // controller.onlineStatus
        controller.usernameField.setText(customer.getUserName());
    }

}
