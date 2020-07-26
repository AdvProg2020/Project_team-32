package View.SupporterPage;

import Server.Model.Chat.ChatWithSupporter;
import Server.Model.Customer;
import View.ChatPage.GUIModels.CustomerStatusCard;
import View.Client;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ChatListPageController implements Initializable {
    public FlowPane chatList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<ChatWithSupporter> chats = getChats();

        for (ChatWithSupporter chat : chats) {
            chatList.getChildren().add(new CustomerStatusCard(chat));
        }

    }

    private ArrayList<ChatWithSupporter> getChats() {
        Client.sendMessage("get chats from supporter",new HashMap<>());
        return (ArrayList<ChatWithSupporter>) Client.getMessage().get("chats");
    }
}
