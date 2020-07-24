package View.ChatPage;

import Server.Model.Chat.ChatBox;
import View.Client;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.InputMismatchException;

public class ChatPageController {

    public TextField messageInput;
    @FXML
    private VBox chatVBox;
    private String  chatBoxId;

    public void sendMessage(ActionEvent actionEvent) {
        if(messageInput.getText().equals("")){
            return;
        }
        HashMap<String , Object> inputs = new HashMap<>();
        inputs.put("chatId", chatBoxId);
        inputs.put("message", messageInput.getText());
        messageInput.setText("");
        Client.sendMessage("send message",inputs);
    }

    public void setChatBoxId(String chatBoxId) {
        this.chatBoxId = chatBoxId;
    }

    public void addMessage(Label label) {
        chatVBox.getChildren().add(label);
    }
}
