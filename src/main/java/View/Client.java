package View;

import Server.Model.*;
import Server.Model.Chat.ChatBox;
import View.ChatPage.GUIModels.ChatStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.HashMap;

public class Client extends Application {

    private final static int PORT_NUMBER = 2046;
    public static Stage primaryStage;
    public static Person user;
    private static Socket clientSocket;
    private static ObjectInputStream clientInputStream;
    private static ObjectOutputStream clientOutputStream;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        URL url = new File("src/main/resources/GUIFiles/main-page.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Shop");
        primaryStage.setScene(new Scene(root, 788, 688));
        primaryStage.setResizable(false);
        primaryStage.show();
//        new ChatStage(new ChatBox("123")).show();
    }

    public static void main(String[] args) {
        connectToServer();
        launch(args);
    }

    private static void connectToServer() {
        try {
            clientSocket = new Socket("localhost", PORT_NUMBER);
            System.out.println("connected");
            clientOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            clientInputStream = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.err.println("error in connection to server.");
        }
    }

    public static void sendMessage(String commandType, HashMap<String, Object> inputs) {

        Message message = new Message();

        message.put("commandType", commandType);

        message.put("token", getToken());

        for (String key : inputs.keySet()) {
            message.put(key, inputs.get(key));
        }

        try {
            clientOutputStream.writeObject(message);
            clientOutputStream.flush();
            clientOutputStream.reset();
        } catch (IOException e) {
            System.err.println("error in write message.");
        }

    }

    public static Message getMessage() {
        Message message = new Message();
        try {
            message = (Message) clientInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("can't get message");
            e.printStackTrace();
        }
        return message;
    }

    private static String getToken() {
        //TODO
        return null;
    }




}
