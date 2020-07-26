package View.ChatPage;

import Server.Model.Chat.ChatWithSupporter;
import View.ChatPage.GUIModels.ChatStage;
import View.ChatPage.GUIModels.CustomerStatusCard;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

public class StartChatCardController {
    public ImageView userImage;
    public Circle onlineStatus;
    public Label usernameField;
    private CustomerStatusCard card;

    public void startChat(ActionEvent actionEvent) {
        ChatWithSupporter chat = card.getChat();
        new ChatStage(chat).show();
    }

    public void setCard(CustomerStatusCard card) {
        this.card = card;
    }
}
