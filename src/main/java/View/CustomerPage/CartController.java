package View.CustomerPage;

import Server.Controller.PurchaseController;
import Server.Controller.AccountController;
import Server.Controller.Controller;
import Server.Model.*;
import View.Client;
import View.IndividualGoodPage.IndividualGoodPageFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
        fixSound();
        cartInitialize();
        increaseIMG.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                increaseObj();
            }
        });
        decreaseIMG.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                decreaseObj();
            }
        });
        totalPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                PurchaseController.passTime();
                HashMap<String, Object> input = new HashMap<>();
                Client.sendMessage("get shoppingBasket price", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    float fPrice = (float) message.get("price");
                    totalPrice.setText(String.valueOf(fPrice));
                }
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
        viewProduct.setOnMouseClicked(e -> {
            viewProductPage();
        });
    }

    private void viewProductPage() {
        try {
            Stage window = new Stage();
            String goodID = cartTable.getSelectionModel().getSelectedItem().getGoodID();
            HashMap<String, Object> input = new HashMap<>();
            input.put("productId", goodID);
            Client.sendMessage("get good by ID from allGoods", input);
            Message message = Client.getMessage();
            if (message.get("status").equals("successful")) {
                Good good = (Good) message.get("good");
                Scene scene = new Scene(IndividualGoodPageFactory.createGoodPage(good));
                window.setScene(scene);
                window.setTitle(good.getName() + " page");
                window.showAndWait();
            } else if (message.get("status").equals("")) {

            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void decreaseObj() {
        String chosenID = cartTable.getSelectionModel().getSelectedItem().getGoodID();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get shoppingBasket list", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            for (ShoppingBasket shoppingBasket : (ArrayList<ShoppingBasket>) message.get("shoppingBasket list")) {
                if (shoppingBasket.getGood().getGoodID().equals(chosenID)) {
                    input = new HashMap<>();
                    input.put("shoppingBasket", shoppingBasket);
                    Client.sendMessage("decrease quantity shoppingBasket", input);
                    message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        new Alert(Alert.AlertType.CONFIRMATION).show();
                        cartTable.getItems().clear();
                        ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
                        input = new HashMap<>();
                        Client.sendMessage("get shoppingBasket list", input);
                        message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            for (ShoppingBasket shoppingBasket1 : (ArrayList<ShoppingBasket>) message.get("shoppingBasket list")) {
                                Good good = shoppingBasket1.getGood();
                                int price = good.getSellerAndPrices().get(shoppingBasket1.getSeller().getUserName());
                                goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket1.getQuantity(), price));
                            }
                            cartTable.setItems(goodsList);
                        }
                    }
                    break;
                }
            }
        } else if (message.get("status").equals("InvalidIDException")) {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    private void increaseObj() {
        String chosenID = cartTable.getSelectionModel().getSelectedItem().getGoodID();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get shoppingBasket list", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            for (ShoppingBasket shoppingBasket : (ArrayList<ShoppingBasket>) message.get("shoppingBasket list")) {
                if (shoppingBasket.getGood().getGoodID().equals(chosenID)) {
                    input = new HashMap<>();
                    message.put("shoppingBasket", shoppingBasket);
                    Client.sendMessage("increase quantity shoppingBasket", input);
                    message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        new Alert(Alert.AlertType.CONFIRMATION).show();
                        cartTable.getItems().clear();
                        ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
                        input = new HashMap<>();
                        Client.sendMessage("get shoppingBasket list", input);
                        message = Client.getMessage();
                        if (message.get("status").equals("successful")) {
                            for (ShoppingBasket shoppingBasket2 : (ArrayList<ShoppingBasket>) message.get("shoppingBasket list")) {
                                Good good = shoppingBasket2.getGood();
                                int price = good.getSellerAndPrices().get(shoppingBasket2.getSeller().getUserName());
                                goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket2.getQuantity(), price));
                            }
                            cartTable.setItems(goodsList);
                        } else if (message.get("status").equals("InvalidIDException")) {
                            new Alert(Alert.AlertType.ERROR).show();
                        }
                    } else if (message.get("status").equals("InvalidIDException")) {
                        new Alert(Alert.AlertType.ERROR).show();
                    }
                    break;
                }
            }
        } else if (message.get("status").equals("InvalidIDException")) {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }

    private void fixSound() {
        purchase.setOnMouseEntered(event -> Controller.sound(1));
        viewProduct.setOnMouseEntered(event -> Controller.sound(1));
        totalPrice.setOnMouseEntered(event -> Controller.sound(2));
        increaseIMG.setOnMouseEntered(event -> Controller.sound(0));
        decreaseIMG.setOnMouseEntered(event -> Controller.sound(0));
        purchase.setOnMouseClicked(event -> Controller.sound(3));
        purchase.setOnMouseClicked(event -> Controller.sound(3));
    }

    private void cartInitialize() {
        try {
            URL url = new File("src\\main\\resources\\GUIFiles\\CustomerIcons\\icons8-add-100.png").toURI().toURL();
            increaseIMG.setImage(new Image(String.valueOf(url)));
            url = new File("src\\main\\resources\\GUIFiles\\CustomerIcons\\icons8-minus-100.png").toURI().toURL();
            decreaseIMG.setImage(new Image(String.valueOf(url)));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        goodIDColumn.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
        goodNameColumn.setCellValueFactory(new PropertyValueFactory<>("GoodName"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("Number"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));

        ObservableList<rowInfo> goodsList = FXCollections.observableArrayList();
        HashMap<String, Object> input = new HashMap<>();
        Client.sendMessage("get shoppingBasket list", input);
        Message message = Client.getMessage();
        if (message.get("status").equals("successful")) {
            for (ShoppingBasket shoppingBasket : (ArrayList<ShoppingBasket>) message.get("shoppingBasket list")) {
                Good good = shoppingBasket.getGood();
                int price = good.getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
                goodsList.add(new rowInfo(good.getGoodID(), good.getName(), shoppingBasket.getQuantity(), price));
            }
            cartTable.setItems(goodsList);
        } else if (message.get("status").equals("InvalidIDException")) {
            new Alert(Alert.AlertType.ERROR).show();
        }

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
