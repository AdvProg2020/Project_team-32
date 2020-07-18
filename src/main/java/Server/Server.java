package Server;

import Server.Controller.AccountController;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;
import java.util.ArrayList;

public class Server {

    private final static int PORT_NUMBER = 2020;
    private static ServerSocket serverSocket;
    private static ArrayList<Socket> allConnectedSockets = new ArrayList<>();

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            waitForClient();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void waitForClient() throws IOException {
        while (true) {
            System.out.println("server waiting for connections...");
            Socket socket = serverSocket.accept();
            System.out.println("client connected.");
            allConnectedSockets.add(socket);
            new ClientHandler(socket).start();
        }
    }

    private static class ClientHandler extends Thread{
        Socket socket;
        ObjectOutputStream clientOutputStream;
        ObjectInputStream clientInputStream;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            clientInputStream = new ObjectInputStream(socket.getInputStream());
            clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            System.out.println("Connection from " + socket.getPort() + "!");
            handleCommands();
        }

        private void handleCommands() {
            JSONObject command;
            while (true){
                try {
                    command = getMessage();
                    if(command.get("commandType").equals("register")){
                        AccountController.register((String) command.get("username"),(String) command.get("userType"),(String) command.get("password"));
                    }
                }
                catch (SecurityException e){
                    //TODO
                }
            }
        }

        private JSONObject getMessage() throws SecurityException {
            JSONObject jsObject = null;
            try {
                jsObject = (JSONObject) clientInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("can't read message");
                e.printStackTrace();
            }
            validateCommand();
            return jsObject;
        }

        private void validateCommand() throws SecurityException{
        }
    }
}
