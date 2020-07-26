package Server.Model;

import Server.Model.Chat.ChatBox;
import Server.Model.Chat.ChatWithSupporter;
import View.ChatPage.GUIModels.ChatStage;

import java.util.ArrayList;

public class Supporter extends Person {

    private ArrayList<ChatWithSupporter> chats;

    public Supporter(String userName, String passWord) {
        super(userName, passWord);
        chats = new ArrayList<>();
    }

    public ChatWithSupporter getChat(Customer customer){
        for (ChatWithSupporter chat : chats) {
            if(chat.getCustomer().userName.equals(customer.userName)){
                return chat;
            }
        }
        ChatWithSupporter newChat = new ChatWithSupporter(customer);
        chats.add(newChat);
        return newChat;
    }

    public ArrayList<ChatWithSupporter> getChats() {
        return chats;
    }
}
