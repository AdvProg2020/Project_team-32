package View.SellerPage;

import Controller.*;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.*;

public class SellerMenuController implements Initializable {

    @FXML
    TabPane sellerTabPane;
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
    TableView tableView;


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
    TextField productID;

    @FXML
    TableView<String> categoryTable;
    @FXML
    TableColumn<String, String> categoryColumn;
    @FXML
    Label balanceLable;

    @FXML
    TextField addProductID_Label;
    @FXML
    Button addProductID_Button;
    @FXML
    Pane addProductPane;


    @FXML
    Label messageRemoveProduct;
    @FXML
    TextField IdRemoveProduct;
    @FXML
    Button RemoveProductButton;

    @FXML
    Pane manageOffPane;

    @FXML
    Button viewOffButton;
    @FXML
    Button editOff;
    @FXML
    Button addOff;
    @FXML
    TextField offID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //info personal

//        AccountController.loggedInUser= new Seller("yasin","moosavi");\


        //company name
        companyNameLabel.setText(((Seller) AccountController.loggedInUser).getFactoryName());

        // sales history
        tableColumn1.setCellValueFactory(new PropertyValueFactory<>("logID"));
        tableColumn2.setCellValueFactory(new PropertyValueFactory<>("buyerUserNmae"));
        tableColumn3.setCellValueFactory(new PropertyValueFactory<>("pricePaid"));
        tableColumn4.setCellValueFactory(new PropertyValueFactory<>("amountReducedForOff"));

        for (SellLog allSellingLog : ((Seller) AccountController.loggedInUser).getAllSellingLogs()) {
            tableView.getItems().add(allSellingLog);
        }

        //manage product
        //
        //
        //
        //


        //view individual product
        viewProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Good good = SellerController.viewProduct(((Seller) AccountController.loggedInUser), productID.getText());
                    TableView productTable = new TableView();
                    TableColumn<String, Good> goodName = new TableColumn<>("good name");
                    TableColumn<String, Good> goodId = new TableColumn<>("good ID");
                    TableColumn<String, Good> point = new TableColumn<>("point");
                    TableColumn<String, Good> categoryName = new TableColumn<>("category name");
                    TableColumn<String, Good> explanations = new TableColumn<>("explanations");
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

        //view product bbuyers

        viewProductBuyers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ArrayList<String> allBuyers = SellerController.viewProductBuyers(((Seller) AccountController.loggedInUser), productID.getText());

                    ObservableList<String> details = FXCollections.observableArrayList(allBuyers);
                    TableView<String> buyersTable = new TableView<>();
                    TableColumn<String, String> col1 = new TableColumn<>("Buyers");
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

        // edit product

        editProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    Good good = SellerController.getGoodFromSellingGood(((Seller) AccountController.loggedInUser), productID.getText());
                    TextField categoryTextField = new TextField("category name");
                    Button setCategory = new Button("press to set category");
                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().addAll(categoryTextField, setCategory);
                    setCategory.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            try {
                                Category category = CategoryController.getCategoryByName(categoryTextField.getText());
                                TextField goodName = new TextField("good name");
                                TextField price = new TextField("price");
                                TextField companyName = new TextField("company name");
                                TextField explanation = new TextField("explanation");
                                TextField[] textFieldProperties = new TextField[category.getSpecialProperties().size()];
                                HashMap<String, String> properties = new HashMap<>();
                                int i = 0;
                                for (String specialProperty : category.getSpecialProperties()) {
                                    textFieldProperties[i] = new TextField(specialProperty);
                                    i++;
                                }
                                Button confirm = new Button("confirm");
                                confirm.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {

                                        int i = 0;
                                        for (String specialProperty : category.getSpecialProperties()) {
                                            properties.put(specialProperty, textFieldProperties[i].getText().trim());
                                            i++;
                                        }
                                        GoodController.editProduct(good, goodName.getText().trim(), companyName.getText().trim(), Integer.parseInt(price.getText().trim())
                                                , ((Seller) AccountController.loggedInUser), explanation.getText().trim(), category, properties);
                                        System.out.println("Request sent successfully.");
                                    }
                                });
                                manageProductPane.getChildren().addAll(goodName, price, companyName, explanation);
                                for (int j = 0; j < category.getSpecialProperties().size(); j++) {
                                    manageProductPane.getChildren().add(textFieldProperties[j]);
                                }


                            } catch (CategoryNotFindException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }


            }
        });


        //Add product

        addProductID_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addProductPane.getChildren().clear();
                Good good_addProduct = Good.getGoodFromAllGoods(addProductID_Label.getText().trim());
                if (good_addProduct != null) {
                    System.out.println("the id is already exist enter a price to add as its seller or 0 to ignore it:");
                    TextField priceTextField = new TextField("enter new price");
                    Button confirmPrice = new Button("confirm price");
                    confirmPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            int price = Integer.parseInt(priceTextField.getText().trim());
                            if (price > 0) {
                                good_addProduct.addSellerAndPrice(AccountController.loggedInUser.getUserName(), price);
                                priceTextField.setText("succesful");
                            }
                        }
                    });
                    addProductPane.getChildren().addAll(priceTextField, confirmPrice);

                } else {

                    TextField categoryTextField = new TextField("category name");
                    Button setCategory = new Button("press to set category");
                    addProductPane.getChildren().clear();
                    addProductPane.getChildren().addAll(categoryTextField, setCategory);
                    setCategory.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            try {
                                Category category = CategoryController.getCategoryByName(categoryTextField.getText());
                                TextField goodName = new TextField("good name");
                                TextField price = new TextField("price");
                                TextField companyName = new TextField("company name");
                                TextField explanation = new TextField("explanation");
                                TextField[] textFieldProperties = new TextField[category.getSpecialProperties().size()];
                                HashMap<String, String> properties = new HashMap<>();
                                int i = 0;
                                for (String specialProperty : category.getSpecialProperties()) {
                                    textFieldProperties[i] = new TextField(specialProperty);
                                    i++;
                                }
                                Button confirm = new Button("confirm");
                                confirm.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {

                                        int i = 0;
                                        for (String specialProperty : category.getSpecialProperties()) {
                                            properties.put(specialProperty, textFieldProperties[i].getText().trim());
                                            i++;
                                        }
                                        GoodController.AddProduct(addProductID_Label.getText().trim(), goodName.getText().trim()
                                                , companyName.getText().trim(), Integer.parseInt(price.getText().trim()),
                                                explanation.getText().trim(), properties, ((Seller) AccountController.loggedInUser)
                                                , category);
                                        System.out.println("Request sent successfully.");
                                    }
                                });
                                addProductPane.getChildren().addAll(goodName, price, companyName, explanation);
                                for (int j = 0; j < category.getSpecialProperties().size(); j++) {
                                    addProductPane.getChildren().add(textFieldProperties[j]);
                                }


                            } catch (CategoryNotFindException e) {
                                e.printStackTrace();
                            }

                        }
                    });


                }
            }
        });


        //remove product


        RemoveProductButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    SellerController.removeProduct(((Seller) AccountController.loggedInUser), IdRemoveProduct.getText().trim());
                    messageRemoveProduct.setText("succesfully deleted the product");
                } catch (InvalidIDException e) {
                    e.printStackTrace();
                    messageRemoveProduct.setText("invalid ID");
                }
            }
        });


        //show categories


        ArrayList<Category> categories = SellerController.showCategory(((Seller) AccountController.loggedInUser));
        ArrayList<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.getName());
        }
        ObservableList<String> details = FXCollections.observableArrayList(categoryNames);
        categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        categoryTable.setItems(details);

        //view off individual

        viewOffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Off off = SellerController.showInddividualOff(((Seller) AccountController.loggedInUser), offID.getText().trim());
                    TableView offTable = new TableView();
                    TableColumn<String, Off> offID = new TableColumn<>("off ID");
                    TableColumn<String, Off> initialDate = new TableColumn<>("initial Date");
                    TableColumn<String, Off> exposeDate = new TableColumn<>("Expopse Date");
                    TableColumn<String, Off> discountPercent = new TableColumn<>("discount");
                    offID.setCellValueFactory(new PropertyValueFactory<>("OffID"));
                    initialDate.setCellValueFactory(new PropertyValueFactory<>("InitialDateString"));
                    exposeDate.setCellValueFactory(new PropertyValueFactory<>("ExposeDateString"));
                    discountPercent.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentString"));

                    tableView.getItems().add(off);

                    manageOffPane.getChildren().clear();
                    manageOffPane.getChildren().add(offTable);
                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }

            }
        });

        //edit off

        editOff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                try {
                    Off off = SellerController.checkOffID(((Seller) AccountController.loggedInUser).getOffs(), offID.getText().trim());
                    TextField goods = new TextField("enter goods in this order GoodId1,goodid2,.. ");
                    final String[] date1 = new String[1];
                    final String[] date2 = new String[1];
                    DatePicker firstDate = new DatePicker();
                    DatePicker lastDate = new DatePicker();
                    firstDate.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            date1[0] = String.valueOf(firstDate.getValue()).replaceAll("-", ",");
                        }
                    });
                    lastDate.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            date2[0] = String.valueOf(lastDate.getValue()).replaceAll("-", ",");
                        }
                    });
                    TextField offPercent = new TextField("enter off percent");
                    Button confirm = new Button("confrim");
                    confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            String input = goods.getText().trim();
                            input += " " + date1[0] + " " + date2[0] + " " + offPercent.getText().trim();

                            String request = null;
                            try {
                                request = SellerController.makeRequest(off.getOffID(), input.trim(), "^(\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)$");
                                RequestController.addEditOffRequest(request, ((Seller) AccountController.loggedInUser));
                            } catch (InvalidPatternException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    manageOffPane.getChildren().clear();
                    manageOffPane.getChildren().addAll(goods, firstDate, lastDate, offPercent, confirm);

                } catch (InvalidIDException e) {
                    e.printStackTrace();
                }

            }
        });

        // add off

        addOff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TextField offID = new TextField("off ID");
                TextField goods = new TextField("enter goods in this order GoodId1,goodid2,.. ");
                final String[] date1 = new String[1];
                final String[] date2 = new String[1];
                DatePicker firstDate = new DatePicker();
                DatePicker lastDate = new DatePicker();
                firstDate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        date1[0] = String.valueOf(firstDate.getValue()).replaceAll("-", ",");
                    }
                });
                lastDate.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        date2[0] = String.valueOf(lastDate.getValue()).replaceAll("-", ",");
                    }
                });
                TextField offPercent = new TextField("enter off percent");
                Button confirm = new Button("confrim");
                confirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        String input = offID.getText().trim() + " " + goods.getText().trim();
                        input += " " + date1[0] + " " + date2[0] + " " + offPercent.getText().trim();
                        String request = null;
                        try {
                            request = SellerController.makeRequest(null, input.trim(), "^(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)$");
                            RequestController.addOffRequest(request.trim(), ((Seller) AccountController.loggedInUser));
                        } catch (InvalidPatternException e) {
                            e.printStackTrace();
                        }

                    }
                });
                manageOffPane.getChildren().clear();
                manageOffPane.getChildren().addAll(offID, goods, firstDate, lastDate, offPercent);

            }
        });


        //all tabs selected initializing


        sellerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {

                //view balance

                if (newTab.equals(balanceTab)) {
                    balanceLable.setText("remaining money :" + ((Seller) AccountController.loggedInUser).getCredit() + "Tomans");
                }
                // off view tab
                else if (newTab.equals(offsTab)) {
                    ArrayList<Off> offs = ((Seller) AccountController.loggedInUser).getOffs();
                    TableView offTable = new TableView();
                    TableColumn<String, Off> offIdColumn = new TableColumn<>("off ID");

                    offIdColumn.setCellValueFactory(new PropertyValueFactory<>("OffID"));
                    for (Off off : offs) {
                        tableView.getItems().add(off);
                    }
                    manageOffPane.getChildren().clear();
                    manageOffPane.getChildren().add(offTable);
                }
                // manage product tab
                else if (newTab.equals(manageProductTab)) {
                    TableView productTable = new TableView();
                    TableColumn<String, Good> goodNameProductTab = new TableColumn<>("good name");
                    TableColumn<String, Good> goodIdProductTab = new TableColumn<>("good ID");
                    goodNameProductTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
                    goodIdProductTab.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
                    for (Good sellingGood : ((Seller) AccountController.loggedInUser).getSellingGoods()) {
                        productTable.getItems().add(sellingGood);
                    }
                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().add(productTable);
                }

            }
        });


    }
}
