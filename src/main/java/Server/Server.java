package Server;

import Server.Controller.*;
import Server.Controller.Exeptions.*;
import Server.Model.*;
import Server.Model.Chat.ChatBox;
import Server.Model.Chat.ChatMessage;
import Server.Model.Chat.ChatWithSupporter;

import org.json.simple.*;

import java.io.*;
import java.lang.SecurityException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Server {
    public static ArrayList<String> allTokens= new ArrayList<>();
    private final static int PORT_NUMBER = 2126;
    private static final int EXPIRE_TIME = (int) 1e9;
    private static ServerSocket serverSocket;
    private static int shopBankID = -1;
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

    private static class ClientHandler extends Thread {
        BankServer bankServer;
        Socket socket;
        Person loggedInUser;
        final String status = "status";
        final String successful = "successful";
        ObjectOutputStream clientOutputStream;
        ObjectInputStream clientInputStream;
        String token =null;
        Date tokenDate =null;

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
            bankServer = new BankServer();
            JSONObject command;
            while (true) {
                try {
                    command = getMessage();
                    switch ((String) command.get("commandType")) {
                        case "register":
                            register(command);
                            break;
                        case "login":
                            login(command);
                            break;
                        case "create supporter":
                            createSupporter(command);
                            break;
                        case "remove user":
                            removeUser(command);
                            break;
                        case "get all categories":
                            getAllCategories();
                            break;
                        case "create manager":
                            createManager(command);
                            break;
                        case "add category":
                            addCategory(command);
                            break;
                        case "get boss status":
                            getBossStatus();
                            break;
                        case "get all persons":
                            getAllPersons();
                            break;
                        case "get all discounts":
                            getAllDiscounts();
                            break;
                        case "edit category":
                            editCategory(command);
                            break;
                        case "remove discount":
                            removeDiscount(command);
                            break;
                        case "get all customers":
                            getAllCustomers();
                            break;
                        case "get loggedInUser":
                            getLoggedInUser();
                            break;
                        case "set imageUrl":
                            setImageUrl(command);
                            break;
                        case "logout" :
                            logout();
                            break;
                        case "remove product by manager":
                            removeProductByManager(command);
                            break;
                        case "get all goods":
                            getAllGoods();
                            break;
                        case "create discount":
                            createDiscount(command);
                            break;
                        case "edit discount":
                            editDiscount(command);
                            break;
                        case "remove category":
                            removeCategory(command);
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
                        case "send message":
                            sendChatMessage(command);
                            break;
                        case "get chat":
                            getChat(command);
                            break;
                        case "edit off":
                            editOff(command);
                            break;
                        case "add off":
                            addOff(command);
                            break;
                        case "get all requests":
                            getAllRequests();
                            break;
                        case "remove request":
                            removeRequest(command);
                            break;
                        case "get all supporters":
                            getAllSupporters();
                            break;
                        case "accept request":
                            acceptRequest(command);
                            break;
                        case "change information":
                            changeInformation(command);
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
                        case "get chats from supporter":
                            getChatLists();
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
                        case "get buylogs":
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
                        case "get discounted price":
                            getDiscountedPrice(command);
                            break;
                        case "pay":
                            checkToken(command);
                            pay(command);
                            break;
                        case "get discount percent":
                            getDiscountPercent(command);
                            break;
                        case "get chat from supporter":
                            getChatFromSupporter(command);
                            break;
                        case "get all categories for good page":
                            getAllCategoriesForGoodPage(command);
                            break;
                        case "get special properties":
                            getSpecialProperties(command);
                            break;
                        case "get general properties":
                            getGeneralProperties(command);
                            return;
                        case "get selected goods":
                            getSelectedGoodsInGoodController();
                            break;
                        case "get selected goods in off controller":
                            getSelectedGoodsInOffController();
                            break;
                        case "get allAuctions":
                            getAllAuctions(command);
                            break;
                        case "transfer from bank to purse":
                            transferFromBankToPurse(command);
                            break;
                        case "transfer from purse to bank":
                            transferFromPurseToBank(command);
                            break;
                        case "add comment":
                            addComment(command);
                            break;
                        case "add to shopping baskets":
                            addToShoppingBaskets(command);
                            break;
                        case "remove from shopping basket":
                            removeFormShoppingBasket(command);
                            break;
                        case "get person by id":
                            getPersonById(command);
                            break;
                        case "get all comments":
                            getAllComments(command);
                            break;
                        case "get wage":
                            getWage(command);
                            break;
                        case "get limit":
                            getLimit(command);
                            break;
                        case "set wage":
                            setWage(command);
                            break;
                        case "set limit":
                            setLimit(command);
                            break;
                        case "update auction":
                            updateAuction(command);
                            break;
                        case "set auction credit":
                            setAuctionCredit(command);
                            break;
                        case "get chatbox auction":
                            getChatboxAuction(command);
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + command.get("commandType"));
                    }
                } catch (SecurityException e) {
                    //TODO
                }catch (TokenException e){
                    //todo logout
                    logout();
                    Message message = new Message();
                    message.put(status,"TokenException");
                    sendMessage(message);

                }
            }
        }

        private void getChatboxAuction(JSONObject command) {
            Message serverAnswer = new Message();
            String id = (String) command.get("id");
            System.out.println(command);
            System.out.println(id);
            System.out.println("*******************************");
            for (Auction auction : Auction.getAuctions()) {
                System.out.println("  ***   "+auction.getID());
                if (auction.getID().equals(id)){
                    serverAnswer.put("chatbox",auction.getChatBox());
                    serverAnswer.put(status,successful);
                    sendMessage(serverAnswer);
                    return;
                }
            }
            serverAnswer.put(status,"something went wrong");
            sendMessage(serverAnswer);
        }

        private void logout() {
            loggedInUser.setStatus(Person.OnlineStatus.OFFLINE);
            loggedInUser = new Guest();
//            token=null;
//            tokenDate =null;
        }

        private void getChatLists() {
            Message serverAnswer = new Message();
            Supporter supporter = (Supporter) loggedInUser;
            serverAnswer.put("chats",supporter.getChats());
            sendMessage(serverAnswer);
        }

        private void getChatFromSupporter(JSONObject command) {
            String supporterName = (String) command.get("supporterName");
            try {
                Supporter supporter = (Supporter) Person.getPersonByUserName(supporterName);
                ChatWithSupporter chat = supporter.getChat((Customer) loggedInUser);
                Message serverAnswer = new Message();
                serverAnswer.put("chat", chat);
                sendMessage(serverAnswer);
            } catch (UserDoesNotExistException e) {
                e.printStackTrace();
            }
        }

        private void setAuctionCredit(JSONObject command) {
            Message message = new Message();
            String ID = (String) command.get("id");
            int credit = (int) command.get("credit");
            if (credit > loggedInUser.getCredit()){
                message.put(status,"you don't have enough money in your purse");

            }else {
                for (Auction auction : Auction.getAuctions()) {
                    if (auction.getID().equals(ID)){
                        if (credit>auction.getPrice()){
                            auction.setBuyerUserName(loggedInUser.getUserName());
                            auction.setPrice(credit);
                            message.put("credit",credit);
                            message.put(status,successful);
                        }
                        else {
                            message.put(status,"you entered less than others");
                        }
                        break;
                    }
                }
            }
            sendMessage(message);
        }

        private void updateAuction(JSONObject command) {
            Message message = new Message();
            String ID = (String) command.get("ID");
            Auction toRemove = null;
            for (Auction auction : Auction.getAuctions()) {
                if (auction.getID().equals(ID)){
                    toRemove=auction;
                    break;
                }
            }
            if (toRemove ==null || toRemove.getExpire().before(new Date())){
                if (toRemove!=null) {
                    PurchaseController.endAuction(toRemove,(Customer)loggedInUser);
                }
                message.put(status,"auction ended");

            }else {
                message.put("price",toRemove.getPrice());
                message.put(status,successful);
            }
            sendMessage(message);
        }

        private void getAllSupporters() {
            Message serverAnswer = new Message();
            serverAnswer.put("all supporters",AccountController.getAllSupporters());
            sendMessage(serverAnswer);
        }

        private void createSupporter(JSONObject command) {
            String username = (String) command.get("username");
            String password = (String) command.get("password");
            Message serverAnswer = new Message();
            try{
                BossController.createSupporter(username, password);
                serverAnswer.put(status, successful);
            } catch (DuplicateUsernameException e) {
                serverAnswer.put(status, "duplicate username");
            }
            finally {
                sendMessage(serverAnswer);
            }
        }

        private void getAllComments(JSONObject command) {
            Message message = new Message();
            Good good = (Good) command.get("good");
            message.put("all comments" ,Good.getGoodById(good.getGoodID()).getAllComments());
            sendMessage(message);
        }

        private void getChat(JSONObject command) {
            String chatId = (String) command.get("chatId");
            ChatBox chatBox = ChatBox.getChatBosFromId(chatId);
            System.out.println(chatBox);
            Message serverAnswer = new Message();
            serverAnswer.put("chat",chatBox);
            sendMessage(serverAnswer);
        }

        private void sendChatMessage(JSONObject command) {
            String chatId = (String) command.get("chatId");
            ChatBox chatBox = ChatBox.getChatBosFromId(chatId);
            String message = (String) command.get("message");
            ChatMessage messageToAdd = new ChatMessage(message, loggedInUser);
            chatBox.add(messageToAdd);
        }

        private void setLimit(JSONObject command) {
            Message message = new Message();
            String  limitString = (String) command.get("limit");
            if (limitString.matches("\\d+")){
                Boss.setLeastMoney(Integer.parseInt(limitString));
                message.put(status,successful);
            }else {
                message.put(status,"wrong input");
            }
            sendMessage(message);
        }

        private void setWage(JSONObject command) {
            Message message = new Message();
            String  wageString = (String) command.get("wage");
            if (wageString.matches("\\d{1}")){
                Boss.setWage(Integer.parseInt(wageString));
                message.put(status,successful);
            }else {
                message.put(status,"wrong input");
            }
            sendMessage(message);
        }

        private void getLimit(JSONObject command) {
            Message message = new Message();
            message.put("limit",Boss.getLeastMoney());
            message.put(status,successful);
            sendMessage(message);
        }

        private void getWage(JSONObject command) {
            Message message = new Message();
            message.put("wage",Boss.getWage());
            message.put(status,successful);
            sendMessage(message);
        }

        private void getPersonById(JSONObject command) {
            Message message = new Message();
            System.out.println("----------------------------------------\n" +
                    "get person by id \n" +
                    (String)command.get("id") + "\n" +
                    "--------------------------------------");
            try {
                message.put("person", Person.getPersonByUserName((String) command.get("id")));
            } catch (UserDoesNotExistException e) {
                e.printStackTrace();
            } finally {
                sendMessage(message);
            }
        }

        private void removeFormShoppingBasket(JSONObject command) {
            List<ShoppingBasket> toRemove = (List<ShoppingBasket>) command.get("list");
            for (ShoppingBasket basket : toRemove) {
                ((ShoppingBasketable)loggedInUser).getShoppingBaskets().removeIf(e -> e.getId() == basket.getId());
            }
        }

        private void addToShoppingBaskets(JSONObject command) {
            ShoppingBasket shoppingBasket = (ShoppingBasket) command.get("shopping basket");
            Good good = (Good) command.get("good");
            Seller seller = (Seller) command.get("seller");
            try {
                ((ShoppingBasketable)loggedInUser).getShoppingBaskets().add(new ShoppingBasket(Good.getGoodById(good.getGoodID()), (Seller)Person.getPersonByUserName(seller.getUserName())));
            } catch (UserDoesNotExistException e) {
                e.printStackTrace();
            }
            System.out.println("-------------------------------------\n" +
                    "add to shopping basket \n" +
                    ((ShoppingBasketable) loggedInUser).getShoppingBaskets() + "\n" +
                    "-------------------------------------------------");
        }


        private void addComment(JSONObject command) {
            Good good = (Good) command.get("good");
            Comment comment = (Comment) command.get("comment");
            System.out.println("--------------------------------------------------\n" +
                    comment + "  " + good.getGoodID() +
                    "----------------------------------------");
            Good.getGoodById(good.getGoodID()).getAllComments().add(comment);
        }

        private void removeProductByManager(JSONObject command) {
            Message serverAnswer = new Message();
            Good goodToRemove = Good.getGoodById((String) command.get("product id"));
            try {
                GoodController.getGoodController().deleteGood(goodToRemove);
                serverAnswer.put(status, successful);
            } catch (NullPointerException e) {
                serverAnswer.put(status, "null pointer exception");
            } finally {
                sendMessage(serverAnswer);
            }

        }

        private void getAllGoods() {
            Message serverAnswer = new Message();
            serverAnswer.put("all goods",Good.getAllGoods());
            sendMessage(serverAnswer);
        }

        private void transferFromPurseToBank(JSONObject command) {
            Message message = new Message();
            String transferType = (String) command.get("transferType");
            float price = (float) command.get("price");
            String result = bankServer(price, transferType);
            if (result.equals("done successfully")) {
                if (loggedInUser.getCredit()-price<Boss.getLeastMoney()){
                    message.put(status,"least money error");
                }else {
                    loggedInUser.setCredit(loggedInUser.getCredit()-price);
                    message.put("credit",loggedInUser.getCredit());
                    message.put(status, successful);
                }
            } else {
                message.put(status, "error");

            }
            sendMessage(message);
        }

        private void transferFromBankToPurse(JSONObject command) {
            Message message = new Message();
            String transferType = (String) command.get("transferType");
            float price = (float) command.get("price");
            String result = bankServer(price, transferType);
            System.out.println("bankserver : " +result );
            if (result.equals("done successfully")) {
                loggedInUser.setCredit(loggedInUser.getCredit()+price);
                message.put("credit",loggedInUser.getCredit());
                message.put(status, successful);
            } else {
                message.put(status, "error");

            }
            sendMessage(message);
        }

        public String bankServer(float totalPrice, String transferType) {
            String result1 = handleAccountID();
            if (result1 != null) return result1;
            bankServer.sendMessageToBank("get_token " + loggedInUser.getUserName() + " " + loggedInUser.getPassWord());
            String result = bankServer.getMessageFromBank();
            System.out.println(" get tokent result : " + result);
            String[] temp = result.split(" ");
            if (temp.length == 1) {
                bankServer.setServerToken(result);
                String home = null;
                String destination = null;
                if (transferType.equals("move")) {
                    home = String.valueOf(bankServer.getAccountID());
                    destination = String.valueOf(shopBankID);
                } else if (transferType.equals("withdraw")) {
                    home = String.valueOf(bankServer.getAccountID());
                    destination = "-1";
                } else if (transferType.equals("deposit")) {
                    home = "-1";
                    destination = String.valueOf(bankServer.getAccountID());
                }
                result = "create_receipt " + bankServer.getServerToken() + " " + transferType + " " + (int) totalPrice + " " +
                        home + " " + destination + " " + "boleshit";
                System.out.println("create_receipt sent to bankserver : " + result);
                bankServer.sendMessageToBank(result);
                String recipt = bankServer.getMessageFromBank();
                System.out.println("receipt answer : " + recipt);
                temp = recipt.split(" ");
                if (temp.length == 1) {
                    bankServer.sendMessageToBank("pay " + recipt);
                    String output = bankServer.getMessageFromBank();
                    if (output.equals("done successfully")) {
                        System.out.println("payed by bank macount succcessfully");
                        return "done successfully";
                    } else if (output.equals("source account does not have enough money")) {
                        System.out.println("source account does not have enough money");
                    } else {
                        System.out.println(output);
                        return output;
                    }
                } else {
                    System.out.println(recipt);
                    return recipt;
                }
            } else {
                System.out.println(result);
                return result;
            }
            return "aaaa";
        }

        private String handleAccountID() {
            if (bankServer.getAccountID() == -1) {
                String message = "create_account " + loggedInUser.getFirstName() + " " + loggedInUser.getLastName()
                        + " " + loggedInUser.getUserName() + " " + loggedInUser.getPassWord() + " " + loggedInUser.getPassWord();
                bankServer.sendMessageToBank(message);
                System.out.println(message);
                String result = bankServer.getMessageFromBank();
                String[] temp = result.split(" ");
                if (temp.length == 1) {
                    bankServer.setAccountID(Integer.parseInt(result));
                    System.out.println(result);
                } else {
                    System.out.println(" handle account : " + result);
                    return result;
                }
            }
            return null;
        }


        private void acceptRequest(JSONObject command) {
            Request request = RequestController.getRequestByIndex((int) command.get("request index"));
            Message serverAnswer = new Message();
            try {
                RequestController.acceptRequest(request);
                serverAnswer.put(status, successful);
            } catch (Exception e) {
                serverAnswer.put(status, "error");
            } finally {
                sendMessage(serverAnswer);
            }
        }

        private void removeRequest(JSONObject command) {
            Request request = RequestController.getRequestByIndex((int) command.get("request index"));
            RequestController.removeRequest(request);
        }

        private void getAllRequests() {
            Message serverAnswer = new Message();
            serverAnswer.put("all requests", RequestController.getAllRequest());
            sendMessage(serverAnswer);
        }

        private void changeInformation(JSONObject command) {
            String email = (String) command.get("email");
            String phone = (String) command.get("phone");
            String lastName = (String) command.get("lastName");
            String firstName = (String) command.get("firstName");
            if (loggedInUser != null)
                AccountController.changeInformation(email, phone, firstName, lastName, loggedInUser);
        }

        private void setImageUrl(JSONObject command) {
            if (loggedInUser != null)
                loggedInUser.setImageUrl((URL) command.get("imageUrl"));
        }

        private void getLoggedInUser() {
            Message serverAnswer = new Message();
            serverAnswer.put("user", loggedInUser);
            sendMessage(serverAnswer);
        }

        private void editCategory(JSONObject command) {
            String curName = (String) command.get("curName");
            String name = (String) command.get("name");
            ArrayList<String> properties = (ArrayList<String>) command.get("properties");
            Message serverAnswer = new Message();
            try {
                Category categoryToChange = CategoryController.getCategoryByName(curName);
                CategoryController.editCategory(categoryToChange, name, properties);
                serverAnswer.put(status, successful);
            } catch (CategoryNotFindException e) {
                serverAnswer.put(status, "category not find");
            } finally {
                sendMessage(serverAnswer);
            }
        }

        private void addCategory(JSONObject command) {
            String categoryName = (String) command.get("name");
            ArrayList<String> properties = (ArrayList<String>) command.get("properties");
            String parentName = (String) command.get("parentName");
            Message serverAnswer = new Message();
            try {
                CategoryController.addCategory(categoryName, properties, parentName);
                serverAnswer.put(status, successful);
            } catch (CategoryNotFindException e) {
                serverAnswer.put(status, "category not find");
            } catch (DuplicateCategoryException e) {
                serverAnswer.put(status, "duplicate category");
            }
            finally {
                sendMessage(serverAnswer);
            }
        }

        private void removeCategory(JSONObject command) {
            String categoryName = (String) command.get("category name");
            Message serverAnswer = new Message();
            try {
                Category category = CategoryController.getCategoryByName(categoryName);
                CategoryController.removeCategory(category);
                serverAnswer.put(status,successful);
            } catch (CategoryNotFindException e) {
                serverAnswer.put(status,"category not find");
            }
            finally {
                sendMessage(serverAnswer);
            }
        }

        private void getAllCategories() {
            Message serverAnswer = new Message();
            serverAnswer.put("all categories", Category.getAllCategories());
            sendMessage(serverAnswer);
        }

        private void editDiscount(JSONObject command) {
            Date exposeDate = (Date) command.get("date");
            String discountId = (String) command.get("discountId");
            int maxDiscountAmount = (int) command.get("maxDiscountAmount");
            int percentInt = (int) command.get("percentInt");
            String curDiscountId = (String) command.get("curDiscountId");
            Message message = new Message();
            try {
                BossController.editDiscount(exposeDate,discountId,maxDiscountAmount,percentInt,curDiscountId);
                message.put(status, successful);
            } catch (DiscountDoesNotExistException e) {
                message.put(status, "discount does not exist");
            }
            finally {
                sendMessage(message);
            }
        }

        private void createDiscount(JSONObject command) {
            Date exposeDate = (Date) command.get("date");
            String discountId = (String) command.get("discountId");
            int maxDiscountAmount = (int) command.get("maxDiscountAmount");
            int percentInt = (int) command.get("percentInt");
            int useNumber = (int) command.get("useNumber");
            ArrayList<Customer> customers = AccountController.getCustomers((ArrayList<String>) command.get("customers"));
            BossController.createDiscount(exposeDate, discountId, maxDiscountAmount, percentInt, useNumber, customers);
            Message message = new Message();
            message.put(status, successful);
            sendMessage(message);
        }

        private void getAllCustomers() {
            Message message = new Message();
            message.put("all customers",  AccountController.getAllCustomer());
            sendMessage(message);
        }

        private void removeDiscount(JSONObject command) {
            Message message = new Message();
            try {
                BossController.removeDiscount((String) command.get("discountId"));
                message.put(status, successful);
            } catch (DiscountDoesNotExistException e) {
                message.put(status, "discount does not exist");
            }
            finally {
                sendMessage(message);
            }
        }

        private void getAllDiscounts() {
            Message message = new Message();
            message.put("all discounts", BossController.getAllDiscount());
            sendMessage(message);
        }

        private void getAllAuctions(JSONObject command) {
            Message message = new Message();
            message.put("allAuctions", Auction.getAuctions());
            message.put(status,successful);
            sendMessage(message);
        }

        private void getSelectedGoodsInOffController() {
            Message message = new Message();
            message.put("selected goods", OffController.getOffController().getSelectedGoods());
            sendMessage(message);
        }

        private void getSelectedGoodsInGoodController() {
            Message message = new Message();
            message.put("selected goods", GoodController.getGoodController().getSelectedGoods());
            sendMessage(message);
        }

        private void getGeneralProperties(JSONObject command) {
            Message message = new Message();
            List<String> properties = Category.getGeneralProperties();
            message.put("special properties", properties);
            sendMessage(message);

        }

        private void getSpecialProperties(JSONObject command) {
            Message message = new Message();
            String selectedCategory = (String) command.get("selected category");
            List<String> properties = Category.getCategoryByName(selectedCategory).getSpecialProperties();
            message.put("special properties", properties);
            sendMessage(message);
        }

        private void getAllCategoriesForGoodPage(JSONObject command) {
            Message message = new Message();
            //TreeItem<String> category = new TreeItem<>("category");
            //Category.rootCategory.getCategory(category);
            message.put("category", Category.rootCategory);
            sendMessage(message);
        }


        private void getDiscountPercent(JSONObject command) {
            Message message = new Message();
            try {
                String discountID = (String) command.get("id");
                message.put("discount percent",PurchaseController.getDiscountPercent(discountID, (Customer)loggedInUser));
                message.put(status, successful);
            } catch (DiscountNotUsableException e) {
                e.printStackTrace();
            } catch (InvalidIDException e) {
                e.printStackTrace();
            } finally {
                sendMessage(message);
            }
        }

        private void pay(JSONObject command) {
            Message message = new Message();
            try {
                Boolean bool =false;
                if (command.get("type").equals("bank")) bool=true;
                String phoneNumber = (String)command.get("phoneNumber");
                String address = (String) command.get("address");
                float price =(float)command.get("price");
                float discountPercent =(float)command.get("discount");
                PurchaseController.payCommand((Customer)loggedInUser, price, discountPercent,bool,address,phoneNumber);
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getDiscountedPrice(JSONObject command) {
            Message message = new Message();
            try {
                float a =(float)command.get("price");
                float b =(float)command.get("discount");
                message.put("discounted price",PurchaseController.getPriceDiscounted(a,b));
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getAllPersons() {
            Message serverAnswer = new Message();
            System.out.println(Person.allPersons);
            serverAnswer.put("all persons", Person.allPersons);
            sendMessage(serverAnswer);
        }

        private void getBossStatus() {
            Message serverAnswer = new Message();
            boolean isBossCreated = Controller.hasBossCreated();
            serverAnswer.put("boss status", isBossCreated);
            sendMessage(serverAnswer);
        }

        private void createManager(JSONObject command) {
            String username = (String) command.get("username");
            String password = (String) command.get("password");
            Message serverAnswer = new Message();
            try {
                BossController.createManager(username, password);
                if (shopBankID ==-1 ) {
                    String message = "create_account " + null + " " + null
                            + " " +  username+ " " + password + " " + password;
                    bankServer.sendMessageToBank(message);
                    System.out.println(message);
                    String result = bankServer.getMessageFromBank();
                    String[] temp = result.split(" ");
                    if (temp.length == 1) {
                        shopBankID = Integer.parseInt(result);
                        System.out.println(result);
                    } else {
                        System.out.println(" handle account : " + result);
                    }
                }
                serverAnswer.put(status, successful);
            } catch (DuplicateUsernameException e) {
                serverAnswer.put(status,"duplicate username");
            }
            finally {
                sendMessage(serverAnswer);
            }
        }

        private void removeUser(JSONObject command) {
            String username = (String) command.get("username");
            Message message = new Message();
            try {
                AccountController.deleteUser(username);
                message.put(status, successful);
            } catch (UserDoesNotExistException e) {
                message.put(status, "username does not exist exception");
            }
            finally {
                sendMessage(message);
            }
        }

        private void getCredit(JSONObject command) {
            Message message=new Message();
            try {
                message.put("credit",loggedInUser.getCredit());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getIndividualBuylog(JSONObject command) {
            Message message=new Message();
            try {
                BuyLog log =CustomerController.getBugLogWithId((String)command.get("ID"),(Customer)loggedInUser);
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
                CustomerController.rateProduct(id,point,(Customer)loggedInUser);
                message.put("buyLogs",((Customer)loggedInUser).getAllBuyLogs());
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
                message.put("buyLogs",((Customer)loggedInUser).getAllBuyLogs());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getCustomerDiscounts(JSONObject command) {
            Message message=new Message();
            try {
                message.put("discounts",((Customer)loggedInUser).getDiscounts());
                System.out.println(message);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getShoppingBasketPrice(JSONObject command) {
            Message message=new Message();
            try {
                float fPrice = PurchaseController.calculatePrice(((Customer)loggedInUser).getShoppingBaskets());
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
                ShoppingBasket shoppingBasket = (ShoppingBasket) command.get("shoppingBasket");
                for (ShoppingBasket basket : ((Customer) loggedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(basket.getGood().getGoodID())){
                        basket.setQuantity(basket.getQuantity() - 1);
                        if (basket.getQuantity()==0) toRemove=basket;
                        break;
                    }
                }
                if (toRemove!=null) ((Customer)loggedInUser).getShoppingBaskets().remove(toRemove);
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void increaseQuantityShoppingBasket(JSONObject command) {
            Message message=new Message();
            try {
                ShoppingBasket shoppingBasket = (ShoppingBasket) command.get("shoppingBasket");
                System.out.println(shoppingBasket.getGood().getGoodID());
                for (ShoppingBasket basket : ((Customer) loggedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(basket.getGood().getGoodID())){
                        basket.setQuantity(basket.getQuantity() + 1);
                        break;
                    }
                }
                System.out.println(command);
                message.put(status,successful);
            } finally {
                System.out.println(message);
                sendMessage(message);
            }
        }

        private void getShoppingBasketList(JSONObject command) {
            Message message=new Message();
            try {
                message.put("shoppingBasket list",((Customer)loggedInUser).getShoppingBaskets());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getShoppingBasketList2(JSONObject command) {
            Message message=new Message();
            try {
                message.put("shoppingBasket list",((Guest)loggedInUser).getShoppingBaskets());
                message.put(status,successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSelllogList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("selllog list", ((Seller) loggedInUser).getAllSellingLogs());
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void login(JSONObject command)  {
            Message message = new Message();
            try {
                loggedInUser = AccountController.login((String) command.get("username"), (String) command.get("password"));
                loggedInUser.setStatus(Person.OnlineStatus.ONLINE);
                message.put(status, successful);
                message.put("user",loggedInUser);
                if (loggedInUser instanceof Boss) {
                    message.put("account type", "boss");
                } else if (loggedInUser instanceof Seller) {
                    message.put("account type", "seller");
                } else if (loggedInUser instanceof Customer) {
                    message.put("account type", "customer");
                } else if (loggedInUser instanceof Supporter){
                    message.put("account type", "supporter");
                }
//                setToken(command);
            } catch (WrongPasswordException e) {
                message.put(status, "wrong password");
            } catch (UserDoesNotExistException e) {
                message.put(status, "user does not exist");
            } finally {
                sendMessage(message);
            }
        }

        private void setToken(JSONObject command) {
            token = generateToken();
            tokenDate = new Date();
        }

        private String generateToken() {
            String madeToken=null;
            SecureRandom random = new SecureRandom();
            while (true){
                byte bytes[] = new byte[20];
                random.nextBytes(bytes);
                madeToken = bytes.toString();
                for (String allToken : allTokens) {
                    if (allToken.equals(madeToken)) continue;
                }
                break;
            }
            allTokens.add(madeToken);
            return madeToken;
        }

        private void setSellerAuction(JSONObject command) {
            Message message = new Message();
            try {
                Good good = ((Seller) loggedInUser).getGoodByID((String) command.get("goodID"));
                Date date = (Date) command.get("date");
                ((Seller) loggedInUser).setAuction(new Auction((Seller) loggedInUser, good,date));
                message.put(status, successful);
            } catch (MultipleAuctionException e) {
                message.put(status, "MultipleAuctionException");
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerCompanyName(JSONObject command) {
            Message message = new Message();
            try {
                message.put("company name", ((Seller) loggedInUser).getFactoryName());
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerCategoryList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("category list", SellerController.showCategory((Seller) loggedInUser));
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerProductList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("product list", ((Seller) loggedInUser).getSellingGoods());
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void getSellerOffList(JSONObject command) {
            Message message = new Message();
            try {
                message.put("off list", ((Seller) loggedInUser).getOffs());
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void addOff(JSONObject command) {
            Message message = new Message();
            try {
                String inputString = (String) command.get("inputString");
                String pattern = (String) command.get("pattern");
                String request = SellerController.makeRequest(inputString, pattern);
                RequestController.addOffRequest(request.trim(), (Seller) loggedInUser);
                message.put(status, successful);
            } catch (InvalidPatternException e) {
                message.put(status, "InvalidPatternException");
            } finally {
                sendMessage(message);
            }
        }

        private void editOff(JSONObject command) {
            Message message = new Message();
            try {
                String inputString = (String) command.get("inputString");
                String pattern = (String) command.get("pattern");
                String request = SellerController.makeRequest(inputString, pattern);
                RequestController.addEditOffRequest(request, (Seller) loggedInUser);
                message.put(status, successful);
            } catch (InvalidPatternException e) {
                message.put(status, "InvalidPatternException");
            } finally {
                sendMessage(message);
            }
        }

        private void sendIndividualOff(JSONObject command) {
            Message message = new Message();
            try {
                Off off = SellerController.getInddividualOff((Seller) loggedInUser, (String) command.get("offId"));
                message.put("off", off);
                message.put(status, successful);
            } catch (InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }

        private void removeProduct(JSONObject command) {
            Message message = new Message();
            try {
                SellerController.removeProduct((Seller) loggedInUser, (String) command.get("productId"));
                message.put(status, successful);
            } catch (InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }

        private void addNewGood(JSONObject command) {
            Message message = new Message();
            try {
                String categoryName = ((Category) command.get("category")).getName();
                Category category = Category.getCategoryByName(categoryName);
                String goodID = (String) command.get("goodId");
                String goodName = (String) command.get("goodName");
                String companyName = (String) command.get("companyName");
                int price = (int) command.get("price");
                String explanatiopn = (String) command.get("explanatiopn");
                HashMap<String, String> properties = (HashMap<String, String>) command.get("properties");
                GoodController.getGoodController().AddProduct(goodID, goodName, companyName,
                        price, explanatiopn, properties, (Seller) loggedInUser, category);
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }

        }

        private void addExistingGood(JSONObject command) {
            Message message = new Message();
            try {
                Good good = Good.getGoodFromAllGoods(((Good) command.get("good")).getGoodID());
                int price = (int) command.get("price");
                good.addSellerAndPrice(loggedInUser.getUserName(), price);
                ((Seller) loggedInUser).getSellingGoods().add(good);
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void goodFromAllGoods(JSONObject command) {
            Message message = new Message();
            try {
                Good good = Good.getGoodFromAllGoods((String) command.get("productId"));
                message.put("good", good);
                message.put(status, successful);
            } finally {
                sendMessage(message);
            }
        }

        private void editProduct(JSONObject command) {
            Message message = new Message();
            try {
                String goodID = ((Good) command.get("good")).getGoodID();
                String categoryName = ((Category) command.get("category")).getName();
                Good good = ((Seller) loggedInUser).getGoodByID(goodID);
                Category category = CategoryController.getCategoryByName(categoryName);
                String goodName = (String) command.get("goodName");
                String companyName = (String) command.get("companyName");
                int price = (int) command.get("price");
                String explanations = (String) command.get("explanatiopn");
                HashMap<String, String> properties = (HashMap<String, String>) command.get("properties");
                GoodController.getGoodController().editProduct(good, goodName, companyName, price
                        , (Seller) loggedInUser, explanations, category, properties);
                message.put(status, successful);
            } catch (CategoryNotFindException e) {
                message.put(status, "CategoryNotFindException");
            } finally {
                sendMessage(message);
            }
        }

        private void getCategoryByName(JSONObject command) {
            Message message = new Message();
            try {
                Category category = CategoryController.getCategoryByName((String) command.get("categoryName"));
                message.put("category", category);
                message.put(status, successful);
            } catch (CategoryNotFindException e) {
                message.put(status, "CategoryNotFindException");
            } finally {
                sendMessage(message);
            }

        }

        private void getProductBuyers(JSONObject command) {
            Message message = new Message();
            try {
                ArrayList<String> allBuyers = SellerController.viewProductBuyers((Seller) loggedInUser, (String) command.get("productId"));
                message.put("allBuyers", allBuyers);
                message.put(status, successful);
            } catch (InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }
        }


        private void getGoodForSeller(JSONObject command) {
            Message message = new Message();
            try {
                Good good = SellerController.viewProduct((Seller) loggedInUser, (String) command.get("productId"));
                message.put("good", good);
                message.put(status, successful);
            } catch (InvalidIDException e) {
                message.put(status, "InvalidIDException");
            } finally {
                sendMessage(message);
            }

        }

        private void register(JSONObject command) {
            Message message = new Message();
            try {
                AccountController.register((String) command.get("username"), (String) command.get("userType"), (String) command.get("password"));
                System.out.println(Person.allPersons);
                message.put(status, successful);
            } catch (DuplicateUsernameException e) {
                message.put(status, "duplicate username exception");
            } finally {
                sendMessage(message);
            }
        }

        private void sendMessage(Message message) {
            try {
                clientOutputStream.writeObject(message);
                clientOutputStream.flush();
                clientOutputStream.reset();
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
            System.out.println(" this shet is from server get message  "+jsObject);
            return jsObject;
        }
        private void checkToken(JSONObject message) throws TokenException {
            if (token!=null && token==message.get("token") && new Date().compareTo(tokenDate)< EXPIRE_TIME){
                tokenDate= new Date();
            }else {
                throw new TokenException();
            }

        }

        private void validateCommand() throws SecurityException {
        }
    }
}
