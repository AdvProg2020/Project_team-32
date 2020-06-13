package View.SellerPage;

import Controller.AccountController;
import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Controller.RequestController;
import Controller.SellerController;
import Model.Good;
import Model.SellLog;
import Model.Seller;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

public class SellerMenuController implements Initializable {
    @FXML
    Tab userMangerTab;
    @FXML
    Tab companyInfoTab;
    @FXML
    Tab salesHIstoryTab;
    @FXML
    Tab manageProductTab;
    @FXML
    Tab addProductTab;
    @FXML
    Tab removeProductTab;
    @FXML
    Tab showCategoryTab;
    @FXML
    Tab offsTab;
    @FXML
    Tab balanceTab;

    @FXML
    Label companyNameLabel;
//    @FXML
//    Label label1 ;
//    @FXML
//    Label label2 ;
//    @FXML
//    Label label3 ;
//    @FXML
//    Label label4 ;
//    @FXML
//    Label label5 ;
//    @FXML
//    Label label6 ;
//    @FXML
//    Label label7 ;


    @FXML
    TableView tableView ;


    @FXML
    TableColumn<String, SellLog> tableColumn1;
    @FXML
    TableColumn<String, SellLog> tableColumn2;
    @FXML
    TableColumn<Float, SellLog> tableColumn3;
    @FXML
    TableColumn<Float, SellLog> tableColumn4;

    @FXML
    Pane manageProductPane;

    @FXML
    Button viewProductButton;
    @FXML
    Button viewProductBuyers;
    @FXML
    Button editProduct;
    @FXML
    Label productID;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //info personal



            //company name
        companyNameLabel.setText(((Seller)AccountController.loggedInUser).getFactoryName());

            // sales history
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("logID"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("buyerUserNmae"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("pricePaid"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("amountReducedForOff"));

        for (SellLog allSellingLog : ((Seller) AccountController.loggedInUser).getAllSellingLogs()) {
            tableView.getItems().add(allSellingLog);
        }

        //manage product begening
        for (Good sellingGood : ((Seller) AccountController.loggedInUser).getSellingGoods()) {
            GridPane gridPane = new GridPane();
            Label name =new Label();
            Label ID = new Label();
            name.setText(sellingGood.getName());
            ID.setText(sellingGood.getGoodID());
            gridPane.add(name,0,0);
            gridPane.add(ID,1,0);
            manageProductPane.getChildren().add(gridPane);
        }

        //view individual product
        viewProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Good good = SellerController.viewProduct(((Seller)AccountController.loggedInUser),productID.getText());
                    TableView productTable = new TableView();
                    TableColumn<String,Good> goodName = new TableColumn<>("good name");
                    TableColumn<String,Good> goodId = new TableColumn<>("good ID");
                    TableColumn<String,Good> point = new TableColumn<>("point");
                    TableColumn<String,Good> categoryName = new TableColumn<>("category name");
                    TableColumn<String,Good> explanations = new TableColumn<>("explanations");
                    goodName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    goodId.setCellValueFactory(new PropertyValueFactory<>("goodID"));
                    categoryName.setCellValueFactory(new PropertyValueFactory<>("categoryString"));
                    explanations.setCellValueFactory(new PropertyValueFactory<>("explanation"));
                    point.setCellValueFactory(new PropertyValueFactory<>("pointString"));

                    tableView.getItems().add(good);

                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().add(productTable);
                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }

            }
        });
        viewProductBuyers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<String> allBuyers = SellerController.viewProductBuyers(((Seller)AccountController.loggedInUser),productID.getText());

                    ObservableList<String> details = FXCollections.observableArrayList(allBuyers);
                    TableView<String> buyersTable = new TableView<>();
                    TableColumn<String,String> col1 = new TableColumn<>("Buyers");
                    buyersTable.getColumns().addAll(col1);
                    col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
                    buyersTable.setItems(details);
                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().add(buyersTable);

                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }


            }
        });
        editProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Good good = SellerController.getGoodFromSellingGood(((Seller) AccountController.loggedInUser),productID.getText());
                    Label goodName = new Label();
                    goodName.setText("good name");
                    Label price = new Label();
                    price.setText("price");
                    Label companyName = new Label();
                    companyName.setText("company name");
                    Label explanation = new Label();
                    explanation.setText("explanation");
                    ArrayList<String> properties = (ArrayList<String>) good.getProperties().keySet();
                    Label[] propert =  new Label[properties.size()-1];
                    for(int i=0 ;i< properties.size();i++){
                        propert[i] = new Label();
                        propert[i].setText(properties.get(i));
                    }
                    Button confirm = new Button("confirm");
                    confirm.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String s ="";
                            s+=goodName.getText().trim()+" "+price.getText().trim()+" "+companyName.getText().trim()+" ";
                            for (int i = 0; i < properties.size(); i++) {
                                s+=properties.get(i).trim()+":"+propert[i].getText().trim()+",";
                            }
                            s+=" "+ explanation.getText().trim();
                            String request = null;
                            try {
                                request = SellerController.makeRequest(good.getGoodID(),s.trim(),"^(\\S+) (\\d+) (\\S+) (\\S+:\\S+,)+ (.+)$");
                                RequestController.addEditProductRequest(request,((Seller)AccountController.loggedInUser));
                            } catch (InvalidPatternException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().addAll(goodName,price,companyName);
                    for (Label label : propert) {
                        manageProductPane.getChildren().addAll(label);
                    }
                    manageProductPane.getChildren().add(explanation);
                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }


            }
        });
        //Add product


















    }
}
