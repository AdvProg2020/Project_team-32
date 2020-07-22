package Server;

import Server.Controller.*;
import Server.Controller.Exeptions.*;
import Server.Model.*;
import org.json.simple.*;

import java.io.*;
import java.lang.SecurityException;
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
                        case "login":
                            login(command);
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
                            break;
                        case "get good by ID from allGoods":
                            goodFromAllGoods(command);
                            break;
                        case "add existing good":
                            addExistingGood(command);
                            break;
                        case "add new product":
                            addNewGood(command);
                            break;
                        case "remove product":
                            removeProduct(command);
                            break;
                        case "get individual off":
                            sendIndividualOff(command);
                            break;
                        case "edit off":
                            editOff(command);
                            break;
                        case "add off":
                            addOff(command);
                            break;
                        case "get seller off list":
                            getSellerOffList(command);
                            break;
                        case "get seller product list":
                            getSellerProductList(command);
                            break;
                        case "get seller category list":
                            getSellerCategoryList(command);
                            break;
                        case "get seller company name":
                            getSellerCompanyName(command);
                            break;
                        case "set auction":
                            setSellerAuction(command);
                            break;
                        case "get seller selllog list":
                            getSelllogList(command);
                            break;
                        case "get shoppingBasket list":
                            getShoppingBasketList(command);
                            break;
                        case "increase quantity shoppingBasket":
                            increaseQuantityShoppingBasket(command);
                            break;
                        case "decrease quantity shoppingBasket":
                            decreaseQuantityShoppingBasket(command);
                            break;
                        case "get shoppingBasket price":
                            getShoppingBasketPrice(command);
                            break;
                        case "get customer discounts":
                            getCustomerDiscounts(command);
                            break;
                        case"get buylogs":
                            getBuylogs(command);
                            break;
                        case "rate":
                            rate(command);
                            break;
                        case "get individual buylog":
                            getIndividualBuylog(command);
                            break;
                        case "getCredit":
                            getCredit(command);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + command.get("commandType"));
                    }
                }
                catch (SecurityException e){
                    //TODO
                }
            }
        }

        private void getCredit(JSONObject command) {
            Message message=new Message();
            try {
                message.put("credit",logedInUser.getCredit());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getIndividualBuylog(JSONObject command) {
            Message message=new Message();
            try {
                BuyLog log =CustomerController.getBugLogWithId((String)command.get("ID"),(Customer)logedInUser);
                message.put("buylog",log);
                message.put(status,successful);
            } catch ( InvalidIDException e) {
                message.put(status,"InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }

        private void rate(JSONObject command) {

            Message message=new Message();
            try {
                String id = (String)command.get("IDrate");
                int point =(int)command.get("pointRate");
                CustomerController.rateProduct(id,point,(Customer)logedInUser);
                message.put("buyLogs",((Customer)logedInUser).getAllBuyLogs());
                message.put(status,successful);
            } catch ( RateException e) {
                message.put(status,"RateException");
            } finally {
                sendMessage(message);
            }


        }

        private void getBuylogs(JSONObject command) {
            Message message=new Message();
            try {
                message.put("buyLogs",((Customer)logedInUser).getAllBuyLogs());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getCustomerDiscounts(JSONObject command) {
            Message message=new Message();
            try {
                message.put("discounts",((Customer)logedInUser).getDiscounts());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getShoppingBasketPrice(JSONObject command) {
            Message message=new Message();
            try {
                float fPrice = PurchaseController.calculatePrice(((Customer)logedInUser).getShoppingBaskets());
                message.put("price",fPrice);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void decreaseQuantityShoppingBasket(JSONObject command) {
            Message message=new Message();
            try {
                ShoppingBasket toRemove =null;
                ShoppingBasket shoppingBasket = (ShoppingBasket) message.get("shoppingBasket");
                for (ShoppingBasket basket : ((Customer) logedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(shoppingBasket.getGood().getGoodID())){
                        basket.setQuantity(basket.getQuantity() - 1);
                        if (basket.getQuantity()==0) toRemove=basket;
                        break;
                    }
                }
                if (toRemove!=null) ((Customer)logedInUser).getShoppingBaskets().remove(toRemove);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void increaseQuantityShoppingBasket(JSONObject command) {
            Message message=new Message();
            try {
                ShoppingBasket shoppingBasket = (ShoppingBasket) message.get("shoppingBasket");
                for (ShoppingBasket basket : ((Customer) logedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(shoppingBasket.getGood().getGoodID())){
                        basket.setQuantity(basket.getQuantity() + 1);
                        break;
                    }
                }
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getShoppingBasketList(JSONObject command) {
            Message message=new Message();
            try {
                message.put("shoppingBasket list",((Customer)logedInUser).getShoppingBaskets());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSelllogList(JSONObject command) {
            Message message=new Message();
            try {
                message.put("selllog list",((Seller)logedInUser).getAllSellingLogs());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void login(JSONObject command) {
            Message message = new Message();
            try {
                logedInUser = AccountController.login((String) command.get("username"),(String) command.get("password"));
                message.put(status, successful);
                if(logedInUser instanceof Boss){
                    message.put("account type", "boss");
                }
                else if(logedInUser instanceof Seller){
                    message.put("account type", "seller");
                }
                else if(logedInUser instanceof Customer){
                    message.put("account type", "customer");
                }
            } catch (WrongPasswordException e) {
                message.put(status, "wrong password");
            } catch (UserDoesNotExistException e) {
                message.put(status, "user does not exist");
            }
            finally {
                sendMessage(message);
            }
        }

        private void setSellerAuction(JSONObject command) {
            Message message = new Message();
            try {
                Good good = ((Seller)logedInUser).getGoodByID((String) command.get("goodID"));
                int port = (int) command.get("port");
                ((Seller)logedInUser).setAuction(new Auction((Seller)logedInUser,good,port));
                message.put(status,successful);
            } catch ( MultipleAuctionException e) {
                message.put(status, "MultipleAuctionException");
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerCompanyName(JSONObject command) {
            Message message = new Message();
            try {
                message.put("company name",((Seller)logedInUser).getFactoryName());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerCategoryList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("category list",SellerController.showCategory((Seller)logedInUser));
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerProductList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("product list",((Seller)logedInUser).getSellingGoods());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerOffList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("off list",((Seller)logedInUser).getOffs());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void addOff(JSONObject command) {
            Message message = new Message();
            try {
                String inputString =(String) command.get("inputString");
                String pattern =(String)command.get("pattern");
                String request = SellerController.makeRequest( inputString,pattern);
                RequestController.addOffRequest(request.trim(), (Seller)logedInUser);
                message.put(status,successful);
            } catch ( InvalidPatternException e) {
                message.put(status, "InvalidPatternException");
            } finally {
                sendMessage(message);
            }
        }

        private void editOff(JSONObject command) {
            Message message = new Message();
            try {
                String inputString =(String) command.get("inputString");
                String pattern =(String)command.get("pattern");
                String request = SellerController.makeRequest(inputString,pattern);
                RequestController.addEditOffRequest(request, (Seller)logedInUser);
                message.put(status,successful);
            } catch ( InvalidPatternException e) {
                message.put(status, "InvalidPatternException");
            } finally {
                sendMessage(message);
            }
        }

        private void sendIndividualOff(JSONObject command) {
            Message message = new Message();
            try {
                Off off = SellerController.getInddividualOff((Seller)logedInUser, (String) command.get("offId"));
                message.put("off",off);
                message.put(status,successful);
            } catch ( InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }

        private void removeProduct(JSONObject command) {
            Message message = new Message();
            try {
                SellerController.removeProduct((Seller)logedInUser, (String) command.get("productId"));
                message.put(status,successful);
            } catch ( InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }

        private void addNewGood(JSONObject command) {
            Message message = new Message();
            try {
                String categoryName = ((Category)command.get("category")).getName();
                Category category =Category.getCategoryByName(categoryName);
                String goodID = (String)command.get("goodId");
                String goodName = (String)command.get("goodName");
                String companyName=(String)command.get("companyName");
                int price = (int)command.get("price");
                String explanatiopn = (String)command.get("explanatiopn");
                HashMap<String,String> properties = (HashMap<String, String>)command.get("properties");
                GoodController.getGoodController().AddProduct(goodID, goodName, companyName,
                        price, explanatiopn, properties, (Seller)logedInUser, category);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }

        }

        private void addExistingGood(JSONObject command) {
            Message message = new Message();
            try {
                Good good= Good.getGoodFromAllGoods(((Good) command.get("good")).getGoodID());
                int price = (int)command.get("price");
                good.addSellerAndPrice(logedInUser.getUserName(), price);
                ((Seller)logedInUser).getSellingGoods().add(good);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void goodFromAllGoods(JSONObject command) {
            Message message = new Message();
            try {
                Good good = Good.getGoodFromAllGoods((String) command.get("productId"));
                message.put("good",good);
                message.put(status,successful);
            } finally {
                sendMessage(message);
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
