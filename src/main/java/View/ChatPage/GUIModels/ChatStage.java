package View.ChatPage.GUIModels;

import Server.Model.Category;
import Server.Model.Chat.ChatBox;
import Server.Model.Chat.ChatMessage;
import View.ChatPage.ChatPageController;
import View.Client;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class ChatStage extends Stage {

    private ChatPageController controller;
    private Parent chatPage;
    private ChatBox chatBox;
    private AnimationTimer pageUpdater;
    private double time;

    public ChatStage(ChatBox chatBox) {

        this.chatBox = chatBox;
        time = 0 ;

        URL url = null;
        try {
            url = new File("src/main/resources/GUIFiles/ChatMenu/Chat-Page.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(url);

        //set main scene
        try {
            Parent parent = loader.load();
            this.controller = loader.getController();
            chatPage = parent;
        } catch (IOException e) {
            e.printStackTrace();
        }
        setScene(new Scene(chatPage));

        //set close event handler
        setOnHidden(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                endStage();
            }
        });

        //set controller fields
        controller.setChatBoxId(chatBox.getChatId());

        initialVBoxContent();

        //set thread for updating
        pageUpdater = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updatePage();
            }
        };
        pageUpdater.start();

    }

    private void updatePage() {
        time += 0.015;
        if(time > 2) {
            ChatBox newChatBos = getMessagesFromServer();
            for (int i = chatBox.size(); i < newChatBos.size(); i++) {
                addMessage(newChatBos.get(i));
            }
            chatBox = newChatBos;
            time = 0;
        }
    }

    private void endStage() {
        pageUpdater.stop();
        System.out.println("stage closed");
    }

    private void initialVBoxContent() {

        chatBox = getMessagesFromServer();
        System.out.println(chatBox);
        for (ChatMessage message : chatBox) {
            controller.addMessage(getLabel(message));
        }

    }

    private Label getLabel(ChatMessage message) {
        Label label = new Label(message.getSender().getUserName() + ": " + message.getMessage());
        return label;
    }

    private void addMessage(ChatMessage message) {
        controller.addMessage(getLabel(message));
    }

    private ChatBox getMessagesFromServer() {
        HashMap<String, Object> inputs = new HashMap<>();
        inputs.put("chatId", chatBox.getChatId());
        Client.sendMessage("get chat", inputs);
        return (ChatBox) Client.getMessage().get("chat");
    }


}
