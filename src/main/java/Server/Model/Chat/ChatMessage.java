package Server.Model.Chat;

import Server.Model.Person;

import java.io.Serializable;

public class ChatMessage implements Serializable {

    private String message;
    private Person sender;

    public ChatMessage(String message, Person sender) {
        this.message = message;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public Person getSender() {
        return sender;
    }
}
