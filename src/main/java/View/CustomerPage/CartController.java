package View.CustomerPage;

import Controller.PurchaseController;
import Controller.AccountController;
import Controller.Controller;
import Model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    public Button purchase;
    public Label totalPrice;
    public TableView<rowInfo> cartTable;
    public TableColumn<rowInfo, String> goodIDColumn;
    public TableColumn<rowInfo, String> goodNameColumn;
    public TableColumn<rowInfo, Integer> numberColumn;
    public TableColumn<rowInfo, Integer> priceColumn;
    public AnchorPane mainPane;
    @FXML
    private ImageView increaseIMG;

    @FXML
    private ImageView decreaseIMG;
    @FXML
    private Button viewProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AccountController.loggedInUser = new Customer("yasin", "123");
        Customer customer = ((Customer) AccountController.loggedInUser);
        Seller seller = new Seller("mamad", "2");
        Good sib = new Good("sib", "1", seller, null, null, null, null, 100);
        Good porteghal = new Good("porthgal", "2", seller, null, null, null, null, 200);
        customer.getShoppingBaskets().add(new ShoppingBasket(sib, seller));
        customer.getShoppingBaskets().add(new ShoppingBasket(porteghal, seller));


        goodIDColumn.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
        goodNameColumn.setCellValueFactory(new PropertyValueFactory<>("GoodName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
//        if (AccountController.loggedInUser instanceof Guest){
//            Guest guest =
//        }else if (AccountController.loggedInUser instanceof Customer){
//
//        }
        for (ShoppingBasket shoppingBasket : ((Customer) AccountController.loggedInUser).getShoppingBaskets()) {
            Good good = shoppingBasket.getGood();
            int price = good.getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
            goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket.getQuantity(), price));
        }
        cartTable.setItems(goodsList);


        try {
            URL url = new File("src\\main\\resources\\GUIFiles\\CustomerIcons\\icons8-add-100.png").toURI().toURL();
            increaseIMG.setImage(new Image(String.valueOf(url)));
            url = new File("src\\main\\resources\\GUIFiles\\CustomerIcons\\icons8-minus-100.png").toURI().toURL();
            decreaseIMG.setImage(new Image(String.valueOf(url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        increaseIMG.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String chosenID = cartTable.getSelectionModel().getSelectedItem().getGoodID();
                for (ShoppingBasket shoppingBasket : ((Customer) AccountController.loggedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(chosenID)) {
                        shoppingBasket.setQuantity(shoppingBasket.getQuantity() + 1);
                        System.out.println("salam1");
                        break;
                    }
                }
                cartTable.getItems().clear();
                ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
                for (ShoppingBasket shoppingBasket : ((Customer) AccountController.loggedInUser).getShoppingBaskets()) {
                    Good good = shoppingBasket.getGood();
                    int price = good.getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
                    goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket.getQuantity(), price));
                }
                cartTable.setItems(goodsList);
            }
        });
        viewProduct.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        });
        decreaseIMG.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String chosenID = cartTable.getSelectionModel().getSelectedItem().getGoodID();
                ShoppingBasket toRemove = null;
                for (ShoppingBasket shoppingBasket : ((Customer) AccountController.loggedInUser).getShoppingBaskets()) {
                    if (shoppingBasket.getGood().getGoodID().equals(chosenID)) {
                        shoppingBasket.setQuantity(shoppingBasket.getQuantity() - 1);
                        System.out.println("salam2");
                        if (shoppingBasket.getQuantity() == 0) {
                            toRemove = shoppingBasket;
                        }
                        break;
                    }
                }
                if (toRemove != null) {
                    ((Customer) AccountController.loggedInUser).getShoppingBaskets().remove(toRemove);
                }
                cartTable.getItems().clear();
                ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
                for (ShoppingBasket shoppingBasket : ((Customer) AccountController.loggedInUser).getShoppingBaskets()) {
                    Good good = shoppingBasket.getGood();
                    int price = good.getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
                    goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket.getQuantity(), price));
                }
                cartTable.setItems(goodsList);
            }
        });
        totalPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PurchaseController.passTime();
                float fPrice = PurchaseController.calculatePrice(((Customer) AccountController.loggedInUser).getShoppingBaskets());
                totalPrice.setText(String.valueOf(fPrice));
            }
        });
        purchase.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                URL infoPaneUrl = null;
                try {
                    infoPaneUrl = new File("src\\main\\resources\\GUIFiles\\Customer-fxml-pages\\Purchase.fxml").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    Pane pane = FXMLLoader.load(infoPaneUrl);
                    mainPane.getChildren().clear();
                    mainPane.getChildren().add(pane);
                    System.out.println("vaty");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }


    public class rowInfo {
        private String goodID;
        private String goodName;
        private int number;
        private int price;

        public rowInfo(String goodID, String goodName, int number, int price) {
            this.goodID = goodID;
            this.goodName = goodName;
            this.number = number;
            this.price = price;
        }

        public String getGoodID() {
            return goodID;
        }

        public String getGoodName() {
            return goodName;
        }

        public int getNumber() {
            return number;
        }

        public int getPrice() {
            return price;
        }


        public void setGoodID(String goodID) {
            this.goodID = goodID;
        }

        public void setGoodName(String goodName) {
            this.goodName = goodName;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
