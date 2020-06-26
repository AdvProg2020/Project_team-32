package View.SellerPage;

import Controller.*;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Model.*;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
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
    @FXML
    ImageView companyImage;


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
    private  void fixSounds(){
        //button enter
        addOff.setOnMouseEntered(event -> Controller.sound(1));
        editOff.setOnMouseEntered(event -> Controller.sound(1));
        viewOffButton.setOnMouseEntered(event -> Controller.sound(1));
        RemoveProductButton.setOnMouseEntered(event -> Controller.sound(1));
        addProductID_Button.setOnMouseEntered(event -> Controller.sound(1));
        editProduct.setOnMouseEntered(event -> Controller.sound(1));
        viewProductBuyers.setOnMouseEntered(event -> Controller.sound(1));
        viewProductButton.setOnMouseEntered(event -> Controller.sound(1));

        //button clicked
        addOff.setOnMouseClicked(event -> Controller.sound(3));
        editOff.setOnMouseClicked(event -> Controller.sound(3));
        viewOffButton.setOnMouseClicked(event -> Controller.sound(3));
        RemoveProductButton.setOnMouseClicked(event -> Controller.sound(3));
        addProductID_Button.setOnMouseClicked(event -> Controller.sound(3));
        editProduct.setOnMouseClicked(event -> Controller.sound(3));
        viewProductBuyers.setOnMouseClicked(event -> Controller.sound(3));
        viewProductButton.setOnMouseClicked(event -> Controller.sound(3));

        //labels
        messageRemoveProduct.setOnMouseEntered(event -> Controller.sound(2));
        balanceLable.setOnMouseEntered(event -> Controller.sound(2));
        companyNameLabel.setOnMouseEntered(event -> Controller.sound(2));
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //info personal
        this.fixSounds();

        /*Seller seller= new Seller("yasin","moosavi");
        AccountController.loggedInUser=seller;
        Seller.seller(seller);
        SellLog  sellLog= new SellLog("sellog",new Date(),100,20,seller.getSellingGoods().get(0),"taha","choert" );
        Good sib = new Good("sib","sibid",seller,"sib sazi"
                ,new Category("sibcat",null,null),
                "sib explanations",null,100);
        ArrayList<Good> goodsArray = new ArrayList<>();
        goodsArray.add(sib);
        Off off = new Off("12",goodsArray,new Date(), new Date(),150);
        seller.getOffs().add(off);
        ArrayList<String> a= new ArrayList<>();
        a.add("food11");
        a.add("food22");
        a.add("food33");
        a.add("food44");
        a.add("food55");
        a.add("food66");
        Category.getAllCategories().add(new Category("cat",a,null));*/

        URL infoTabURL = null;
        try {
            infoTabURL = new File("src\\main\\resources\\GUIFiles\\personal-info.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            userMangerTab.setContent(FXMLLoader.load(infoTabURL));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // company name
        companyNameLabel.setText(((Seller) AccountController.loggedInUser).getFactoryName());



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
                    productTable.setPrefWidth(640);
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

                    goodName.prefWidthProperty().bind(productTable.widthProperty().multiply(0.2));
                    goodId.prefWidthProperty().bind(productTable.widthProperty().multiply(0.2));
                    point.prefWidthProperty().bind(productTable.widthProperty().multiply(0.2));
                    categoryName.prefWidthProperty().bind(productTable.widthProperty().multiply(0.2));
                    explanations.prefWidthProperty().bind(productTable.widthProperty().multiply(0.2));

                    productTable.getColumns().addAll(goodName,goodId,categoryName,explanations,point);

                    System.out.println("salam");

                    productTable.getItems().add(good);
                    productTable.setMinSize(600,500);
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
                    for (String allBuyer : allBuyers) {
                        System.out.println(allBuyer);
                    }
                    ObservableList<String> details = FXCollections.observableArrayList(allBuyers);
                    TableView<String> buyersTable = new TableView<>();
                    TableColumn<String, String> col1 = new TableColumn<>("Buyers");
                    col1.prefWidthProperty().bind(buyersTable.widthProperty().multiply(1));
                    buyersTable.getColumns().add(col1);
                    col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
                    buyersTable.setItems(details);
                    buyersTable.setPrefWidth(640);
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
                    VBox vBox = new VBox();
                    vBox.setLayoutX(200);
                    vBox.getChildren().clear();
                    vBox.getChildren().addAll(categoryTextField,setCategory);
                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().addAll(vBox);
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
                                        new Alert(Alert.AlertType.CONFIRMATION).show();
                                    }
                                });
                                vBox.getChildren().addAll(goodName, price, companyName, explanation,confirm);
                                manageProductPane.getChildren().clear();
                                for (int j = 0; j < category.getSpecialProperties().size(); j++) {
                                    vBox.getChildren().add(textFieldProperties[j]);
                                }
                                manageProductPane.getChildren().addAll(vBox);


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
                    GridPane gridPane = new GridPane();
                    gridPane.setLayoutX(200);
                    gridPane.setLayoutY(200);
                    gridPane.setVgap(10);
                    gridPane.add(priceTextField,0,0);
                    gridPane.add(confirmPrice,0,1);
                    addProductPane.getChildren().addAll(gridPane);

                } else {
                    TextField categoryTextField = new TextField("category name");
                    Button setCategory = new Button("press to set category");
                    addProductPane.getChildren().clear();
                    VBox box = new VBox();
                    box.setLayoutX(200);
                    box.getChildren().addAll(categoryTextField,setCategory);
                    addProductPane.getChildren().addAll(box);
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
                                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                        alert.show();
                                    }
                                });
                                box.getChildren().addAll(goodName,price,companyName,explanation,confirm);
                                addProductPane.getChildren().clear();
                                for (int j = 0; j < category.getSpecialProperties().size(); j++) {
                                    box.getChildren().add(textFieldProperties[j]);
                                }
                                addProductPane.getChildren().add(box);


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




        //view off individual

        viewOffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Off off = SellerController.showInddividualOff(((Seller) AccountController.loggedInUser), offID.getText().trim());
                    TableView offTable = new TableView();
                    offTable.setPrefWidth(640);
                    offTable.setLayoutY(20);
                    TableColumn<String, Off> offID = new TableColumn<>("off ID");
                    TableColumn<String, Off> initialDate = new TableColumn<>("initial Date");
                    TableColumn<String, Off> exposeDate = new TableColumn<>("Expopse Date");
                    TableColumn<String, Off> discountPercent = new TableColumn<>("discount");
                    offID.setCellValueFactory(new PropertyValueFactory<>("OffID"));
                    initialDate.setCellValueFactory(new PropertyValueFactory<>("InitialDateString"));
                    exposeDate.setCellValueFactory(new PropertyValueFactory<>("ExposeDateString"));
                    discountPercent.setCellValueFactory(new PropertyValueFactory<>("DiscountPercentString"));

                    offID.prefWidthProperty().bind(offTable.widthProperty().multiply(0.3));
                    initialDate.prefWidthProperty().bind(offTable.widthProperty().multiply(0.3));
                    exposeDate.prefWidthProperty().bind(offTable.widthProperty().multiply(0.2));
                    discountPercent.prefWidthProperty().bind(offTable.widthProperty().multiply(0.2));

                    offTable.getColumns().addAll(offID,initialDate,exposeDate,discountPercent);
                    offTable.getItems().add(off);
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
                    GridPane gridPane = new GridPane();
                    gridPane.setLayoutX(100);
                    gridPane.setLayoutY(50);
                    gridPane.setHgap(10);
                    gridPane.setVgap(20);
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPrefWidth(400);
                    gridPane.getColumnConstraints().add(cc);
                    gridPane.setAlignment(Pos.TOP_CENTER);
                    gridPane.add(goods,0,0);
                    gridPane.add(firstDate,0,1);
                    gridPane.add(lastDate,0,2);
                    gridPane.add(offPercent,0,3);
                    gridPane.add(confirm,0,4);
                    manageOffPane.getChildren().clear();
                    manageOffPane.getChildren().addAll(gridPane);

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
                firstDate.setMinWidth(400);
                DatePicker lastDate = new DatePicker();
                lastDate.setMinWidth(400);
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
                            System.out.println("off added");
                        } catch (InvalidPatternException e) {
                            e.printStackTrace();
                        }

                    }
                });

                GridPane gridPane = new GridPane();
                gridPane.setLayoutX(100);
                gridPane.setLayoutY(50);
                gridPane.setHgap(10);
                gridPane.setVgap(20);
                ColumnConstraints cc = new ColumnConstraints();
                cc.setPrefWidth(400);
                gridPane.getColumnConstraints().add(cc);
                gridPane.setAlignment(Pos.TOP_CENTER);
                gridPane.add(offID,0,0);
                gridPane.add(goods,0,1);
                gridPane.add(firstDate,0,2);
                gridPane.add(lastDate,0,3);
                gridPane.add(offPercent,0,4);
                gridPane.add(confirm,0,5);
                manageOffPane.getChildren().clear();
                manageOffPane.getChildren().addAll(gridPane);

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
                    offTable.setPrefWidth(640);
                    TableColumn<String, Off> offIdColumn = new TableColumn<>("off ID");
                    offIdColumn.setCellValueFactory(new PropertyValueFactory<>("OffID"));
                    offIdColumn.prefWidthProperty().bind(offTable.widthProperty().multiply(1));
                    offTable.getColumns().add(offIdColumn);
                    for (Off off : offs) {
                        offTable.getItems().add(off);
                    }
                    manageOffPane.getChildren().clear();
                    manageOffPane.getChildren().add(offTable);
                }
                // manage product tab
                else if (newTab.equals(manageProductTab)) {
                    TableView productTable = new TableView();
                    productTable.setPrefWidth(650);
                    TableColumn<String, Good> goodNameProductTab = new TableColumn<>("good name");
                    TableColumn<String, Good> goodIdProductTab = new TableColumn<>("good ID");
                    goodNameProductTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
                    goodIdProductTab.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
                    productTable.getColumns().addAll(goodNameProductTab,goodIdProductTab);
                    goodNameProductTab.prefWidthProperty().bind(productTable.widthProperty().multiply(0.5));
                    goodIdProductTab.prefWidthProperty().bind(productTable.widthProperty().multiply(0.5));
//                    goodNameProductTab.setResizable(false);
//                    goodIdProductTab.setResizable(false);

                    for (Good sellingGood : ((Seller) AccountController.loggedInUser).getSellingGoods()) {
                        productTable.getItems().add(sellingGood);
                    }
                    manageProductPane.getChildren().clear();
                    manageProductPane.getChildren().add(productTable);
                }
                // sales history
                else if(newTab.equals(salesHIstoryTab)){
                    tableColumn1.setCellValueFactory(new PropertyValueFactory<>("logID"));
                    tableColumn2.setCellValueFactory(new PropertyValueFactory<>("buyerUserNmae"));
                    tableColumn3.setCellValueFactory(new PropertyValueFactory<>("pricePaid"));
                    tableColumn4.setCellValueFactory(new PropertyValueFactory<>("amountReducedForOff"));

                    tableColumn1.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
                    tableColumn2.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
                    tableColumn3.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
                    tableColumn4.prefWidthProperty().bind(tableView.widthProperty().multiply(0.2));
                    tableColumn1.setResizable(false);
                    tableColumn2.setResizable(false);
                    tableColumn3.setResizable(false);
                    tableColumn4.setResizable(false);
                    for (SellLog allSellingLog : ((Seller) AccountController.loggedInUser).getAllSellingLogs()) {
                        tableView.getItems().add(allSellingLog);
                    }
                }
                //show categories
                else if(newTab.equals(showCategoryTab)){
                    ArrayList<Category> categories = SellerController.showCategory(((Seller) AccountController.loggedInUser));
                    ArrayList<String> categoryNames = new ArrayList<>();
                    for (Category category : categories) {
                        categoryNames.add(category.getName());
                    }
                    ObservableList<String> details = FXCollections.observableArrayList(categoryNames);
                    categoryColumn = new TableColumn<>();
                    categoryColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
                    categoryColumn.prefWidthProperty().bind(categoryTable.widthProperty().multiply(1));
                    categoryColumn.setResizable(false);
                    categoryTable.getColumns().clear();
                    categoryTable.getColumns().add(categoryColumn);
                    categoryTable.setItems(details);
                }

            }
        });


    }
}
