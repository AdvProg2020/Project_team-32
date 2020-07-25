package Server.Model.Chat;

import java.util.ArrayList;

public class ChatBox extends ArrayList<ChatMessage> {

    private String chatId;
    private static ArrayList<ChatBox> allChatBoxes = new ArrayList<>();

    public ChatBox() {
        this.chatId = generateId();
        allChatBoxes.add(this);
    }

    public String getChatId() {
        return chatId;
    }

    public static ChatBox getChatBosFromId(String id){
        for (ChatBox chatBox : allChatBoxes) {
            if(id.equals(chatBox.chatId)){
                return chatBox;
            }
        }
        return null;
    }

    private static String generateId(){
        String validChar = new String();
        return null;
    }

}
