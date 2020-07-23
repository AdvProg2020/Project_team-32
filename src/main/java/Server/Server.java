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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

    private static class ClientHandler extends Thread {

        Socket socket;
        Person loggedInUser;
        final String status = "status";
        final String successful = "successful";
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
                        case "get discounted price":
                            getDiscountedPrice(command);
                            break;
                        case "pay by wallet":
                            payByWallet(command);
                            break;
                        case "get discount percent":
                            getDiscountPercent(command);
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
                        default:
                            throw new IllegalStateException("Unexpected value: " + command.get("commandType"));
                    }
                } catch (SecurityException e) {
                    //TODO
                }
            }
        }

        private void editCategory(JSONObject command) {
            String curName = (String) command.get("curName");
            String name = (String) command.get("name");
            ArrayList<String> properties = (ArrayList<String>) command.get("properties");
            Message serverAnswer = new Message();
            try {
                Category categoryToChange = CategoryController.getCategoryByName(curName);
                CategoryController.editCategory(categoryToChange,name,properties);
                serverAnswer.put(status, successful);
            } catch (CategoryNotFindException e) {
                serverAnswer.put(status, "category not find");
            }
            finally {
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
            ArrayList<Customer> customers = AccountController.getCustomers((ArrayList<Customer>) command.get("customers"));
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

        private void payByWallet(JSONObject command) {
            Message message = new Message();
            try {
                float a =(float)command.get("price");
                float b =(float)command.get("discount");
                PurchaseController.payCommand((Customer)loggedInUser, a, b);
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
                BossController.createManager(username,password);
                serverAnswer.put(status,successful);
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
                ShoppingBasket shoppingBasket = (ShoppingBasket) message.get("shoppingBasket");
                for (ShoppingBasket basket : ((Customer) loggedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(shoppingBasket.getGood().getGoodID())){
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
                ShoppingBasket shoppingBasket = (ShoppingBasket) message.get("shoppingBasket");
                for (ShoppingBasket basket : ((Customer) loggedInUser).getShoppingBaskets()) {
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
                message.put("shoppingBasket list",((Customer)loggedInUser).getShoppingBaskets());
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

        private void login(JSONObject command) {
            Message message = new Message();
            try {
                loggedInUser = AccountController.login((String) command.get("username"), (String) command.get("password"));
                message.put(status, successful);
                if (loggedInUser instanceof Boss) {
                    message.put("account type", "boss");
                } else if (loggedInUser instanceof Seller) {
                    message.put("account type", "seller");
                } else if (loggedInUser instanceof Customer) {
                    message.put("account type", "customer");
                }
            } catch (WrongPasswordException e) {
                message.put(status, "wrong password");
            } catch (UserDoesNotExistException e) {
                message.put(status, "user does not exist");
            } finally {
                sendMessage(message);
            }
        }

        private void setSellerAuction(JSONObject command) {
            Message message = new Message();
            try {
                Good good = ((Seller) loggedInUser).getGoodByID((String) command.get("goodID"));
                int port = (int) command.get("port");
                String ID="sss";
                //todo make id for auctions;
                ((Seller) loggedInUser).setAuction(new Auction(ID,(Seller) loggedInUser, good, port));
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

        private void validateCommand() throws SecurityException {
        }
    }
}
