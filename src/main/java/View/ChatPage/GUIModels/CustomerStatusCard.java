package View.ChatPage.GUIModels;

import Server.Model.Chat.ChatWithSupporter;
import Server.Model.Customer;
import View.ChatPage.StartChatCardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CustomerStatusCard extends Pane {

    private StartChatCardController controller;
    private ChatWithSupporter chat;

    public CustomerStatusCard(ChatWithSupporter chat) {

        Customer customer = chat.getCustomer();
        this.chat = chat;

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

        initialPaneFields(customer);

        controller.setCard(this);

    }

    private void initialPaneFields(Customer customer) {
        controller.userImage = new ImageView(String.valueOf(customer.getImageUrl()));
        String status = customer.getStatus();
        controller.onlineStatus.setFill(status.equals("ONLINE")? Paint.valueOf("#008904") : Paint.valueOf("#ea0000"));
        controller.usernameField.setText(customer.getUserName());
    }

    public ChatWithSupporter getChat() {
        return chat;
    }
}
