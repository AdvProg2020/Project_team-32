package Server;

import Server.Controller.AccountController;
import Server.Controller.CategoryController;
import Server.Controller.Exeptions.CategoryNotFindException;
import Server.Controller.Exeptions.DuplicateUsernameException;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.GoodController;
import Server.Controller.SellerController;
import Server.Model.*;
import org.json.simple.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
        Person logedInUser;
        final String status ="status";
        final String successful ="successful";
        ObjectOutputStream clientOutputStream;
        ObjectInputStream clientInputStream;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;
            clientInputStream = new ObjectInputStream(socket.getInputStream());
            clientOutputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            //TODO initialize and import database
            System.out.println("Connection from " + socket.getPort() + "!");
            handleCommands();
        }



        private void handleCommands() {
            JSONObject command;
            while (true){
                try {
                    command = getMessage();
                    switch ((String) command.get("commandType")){
                        case "register":
                            register(command);
                            break;
                        case "get good by ID":
                            getGoodForSeller(command);
                            break;
                        case "get product buyers by id":
                            getProductBuyers(command);
                            break;
                        case "get category by name":
                            getCategoryByName(command);
                            break;
                        case "edit product":
                            editProduct(command);
                        default:
                            throw new IllegalStateException("Unexpected value: " + command.get("commandType"));
                    }
                }
                catch (SecurityException e){
                    //TODO
                }
            }
        }

        private void editProduct(JSONObject command) {
            Message message = new Message();
            try {
                String goodID =((Good)command.get("good")).getGoodID();
                String categoryName =((Category) command.get("category")).getName();
                Good good = ((Seller)logedInUser).getGoodByID(goodID);
                Category category = CategoryController.getCategoryByName(categoryName);
                String goodName = (String) command.get("goodName");
                String companyName =(String)command.get("companyName");
                int price =(int) command.get("price");
                String explanations = (String) command.get("explanatiopn");
                HashMap<String,String> properties = (HashMap<String, String>)command.get("properties");
                GoodController.getGoodController().editProduct(good, goodName, companyName, price
                        , (Seller)logedInUser, explanations, category, properties);
                message.put(status,successful);
            } catch ( CategoryNotFindException e) {
                message.put(status, "CategoryNotFindException");
            } finally {
                sendMessage(message);
            }
        }


        private void getCategoryByName(JSONObject command) {
            Message message = new Message();
            try {
                Category category = CategoryController.getCategoryByName((String) command.get("categoryName"));
                message.put("category",category);
                message.put(status,successful);
            } catch (CategoryNotFindException e) {
                message.put(status, "CategoryNotFindException");
            } finally {
                sendMessage(message);
            }

        }

        private void getProductBuyers(JSONObject command) {
            Message message = new Message();
            try {
                ArrayList<String> allBuyers = SellerController.viewProductBuyers((Seller) logedInUser, (String) command.get("productId"));
                message.put("allBuyers",allBuyers);
                message.put(status,successful);
            } catch ( InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }


        private void getGoodForSeller(JSONObject command)  {
            Message message = new Message();
            try {
                Good good = SellerController.viewProduct((Seller) logedInUser, (String) command.get("productId"));
                message.put("good",good);
                message.put(status,successful);
            } catch ( InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }

        }
        private void register(JSONObject command)  {
            Message message = new Message();
            try {
               AccountController.register((String) command.get("username"),(String) command.get("userType"),(String) command.get("password"));
               message.put(status,successful);
           }
           catch (DuplicateUsernameException e) {
                message.put(status,"duplicate username exception");
           }
            finally {
                sendMessage(message);
            }
        }

        private void sendMessage(Message message){
            try {
                clientOutputStream.writeObject(message);
            } catch (IOException e) {
                System.err.println("can't send message.");
                e.printStackTrace();
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
