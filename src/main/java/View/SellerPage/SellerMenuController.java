package View.SellerPage;

import Server.Controller.*;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.Exeptions.InvalidPatternException;
import Server.Model.*;
import View.Client;
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
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SellerMenuController implements Initializable {

    public Tab logOutTab;
    public Tab offsPageTab;
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

    private void fixSounds() {
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
        this.fixSounds();
        viewProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String, Object> input = new HashMap<>();
                input.put("productId", productID.getText());
                Client.sendMessage("get good by ID", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    makeProductTable(manageProductPane, (Good) message.get("good"));
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("wrong product id");
                }
            }
        });
        viewProductBuyers.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String, Object> inputs = new HashMap<>();
                inputs.put("productId", productID.getText());
                Client.sendMessage("get product buyers by id", inputs);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    makeBuyersTable(manageProductPane, (ArrayList<String>) message.get("allBuyers"));
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("wrong product id");
                }
            }
        });
        editProduct.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HashMap<String, Object> input = new HashMap<>();
                input.put("productId", productID.getText());
                Client.sendMessage("get good by ID", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    editProduct(manageProductPane, (Good) message.get("good"));
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("wrong product id");
                }
            }
        });
        addProductID_Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                Good good_addProduct = Good.getGoodFromAllGoods(addProductID_Label.getText().trim());
                HashMap<String, Object> input = new HashMap<>();
                input.put("productId", productID.getText());
                Client.sendMessage("get good by ID from allGoods", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    addProduct((Good) message.get("good"), addProductPane, addProductID_Label);
                }

            }
        });
        RemoveProductButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                SellerController.removeProduct(((Seller) AccountController.loggedInUser), IdRemoveProduct.getText().trim());
                HashMap<String, Object> input = new HashMap<>();
                input.put("productId", IdRemoveProduct.getText().trim());
                Client.sendMessage("remove product", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    showConfirmationAlert("you removed good successfully");
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("InvalidIDException");
                }
            }
        });
        viewOffButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                Off off = SellerController.showInddividualOff(((Seller) AccountController.loggedInUser), offID.getText().trim());
                HashMap<String, Object> input = new HashMap<>();
                input.put("offId", offID.getText().trim());
                Client.sendMessage("view individual off", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    viewIndividualOff((Off) message.get("off"), manageOffPane);
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("InvalidIDException");
                }

            }
        });
        editOff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //                    Off off = SellerController.checkOffID(((Seller) AccountController.loggedInUser).getOffs(), offID.getText().trim());
                HashMap<String, Object> input = new HashMap<>();
                input.put("offId", offID.getText().trim());
                Client.sendMessage("edit off", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    editOff((Off) message.get("off"), manageOffPane);
                } else if (message.get("status").equals("InvalidIDException")) {
                    showErrorAlert("InvalidIDException");
                }
            }
        });
        addOff.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addOff(manageOffPane);
            }
        });

        sellerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {

                if (newTab.equals(userMangerTab)) {
                    userManagerTabOnClick(userMangerTab);
                } else if (newTab.equals(balanceTab)) {
                    balanceLable.setText("remaining money :" + ((Seller) AccountController.loggedInUser).getCredit() + "Tomans");
                } else if (newTab.equals(offsTab)) {
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get seller off list", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        setAllOffs((ArrayList<Off>) message.get("off list"), manageOffPane);
                    }
                } else if (newTab.equals(manageProductTab)) {
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get seller product list", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        setAllProducts((ArrayList<Good>) message.get("product list"), manageProductPane);
                    }
                } else if (newTab.equals(salesHIstoryTab)) {
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get seller selllog list", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        setSalesHistoryTable((ArrayList<SellLog>) message.get("selllog list"), tableColumn1, tableColumn2, tableColumn3, tableColumn4, tableView);
                    }
                } else if (newTab.equals(showCategoryTab)) {
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get seller category list", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        setAllCategories((ArrayList<Category>) message.get("category list"));
                    }
                } else if (newTab.equals(logOutTab)) {
                    logOutTabOnClick(logOutTab);
                } else if (newTab.equals(offsPageTab)) {
                    offsPageTabOnClick(offsPageTab);
                }
                else if (newTab.equals(companyInfoTab)){
//                    companyNameLabel.setText(((Seller) AccountController.loggedInUser).getFactoryName());
                    HashMap<String, Object> input = new HashMap<>();
                    Client.sendMessage("get seller company name", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        setAllCategories((ArrayList<Category>) message.get("company name"));
                    }

                }

            }
        });


    }

    private static void userManagerTabOnClick(Tab userMangerTab) {
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
    }

    private static void offsPageTabOnClick(Tab offsPageTab) {
        try {
            URL url = new File("src\\main\\resources\\GUIFiles\\OffsPage.fxml").toURI().toURL();
            offsPageTab.setContent(FXMLLoader.load(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logOutTabOnClick(Tab logOutTab) {
        URL url = null;
        try {
            url = new File("src\\main\\resources\\GUIFiles\\logout-page.fxml").toURI().toURL();
            logOutTab.setContent(FXMLLoader.load(url));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAllCategories(ArrayList<Category> categories) {
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

    private static void setSalesHistoryTable(ArrayList<SellLog> sellLogArrayList, TableColumn<String, SellLog> tableColumn1, TableColumn<String, SellLog> tableColumn2, TableColumn<Float, SellLog> tableColumn3, TableColumn<Float, SellLog> tableColumn4, TableView tableView) {
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
        for (SellLog allSellingLog : sellLogArrayList) {
            tableView.getItems().add(allSellingLog);
        }
    }

    private static void setAllProducts(ArrayList<Good> selingGoods, Pane manageProductPane) {
        TableView productTable = new TableView();
        productTable.setPrefWidth(650);
        TableColumn<String, Good> goodNameProductTab = new TableColumn<>("good name");
        TableColumn<String, Good> goodIdProductTab = new TableColumn<>("good ID");
        goodNameProductTab.setCellValueFactory(new PropertyValueFactory<>("Name"));
        goodIdProductTab.setCellValueFactory(new PropertyValueFactory<>("GoodID"));
        productTable.getColumns().addAll(goodNameProductTab, goodIdProductTab);
        goodNameProductTab.prefWidthProperty().bind(productTable.widthProperty().multiply(0.5));
        goodIdProductTab.prefWidthProperty().bind(productTable.widthProperty().multiply(0.5));
        for (Good sellingGood : selingGoods) {
            productTable.getItems().add(sellingGood);
        }
        manageProductPane.getChildren().clear();
        manageProductPane.getChildren().add(productTable);
    }

    private static void setAllOffs(ArrayList<Off> offs, Pane manageOffPane) {
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

    private static void addOff(Pane manageOffPane) {
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
                String inputString = offID.getText().trim() + " " + goods.getText().trim();
                inputString += " " + date1[0] + " " + date2[0] + " " + offPercent.getText().trim();
                HashMap<String, Object> inputs = new HashMap<>();
                inputs.put("inputString", inputString.trim());
                inputs.put("pattern", "(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)");
                Client.sendMessage("make add off request", inputs);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    showConfirmationAlert("you successfully made a add off request");
                } else if (message.get("status").equals("InvalidPatternException")) {
                    showErrorAlert("entered pattern is wrong");
                }
//                    request = SellerController.makeRequest( inputString.trim(), "(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)");
//                    RequestController.addOffRequest(request.trim(), ((Seller) AccountController.loggedInUser));
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
        gridPane.add(offID, 0, 0);
        gridPane.add(goods, 0, 1);
        gridPane.add(firstDate, 0, 2);
        gridPane.add(lastDate, 0, 3);
        gridPane.add(offPercent, 0, 4);
        gridPane.add(confirm, 0, 5);
        manageOffPane.getChildren().clear();
        manageOffPane.getChildren().addAll(gridPane);
    }

    private static void viewIndividualOff(Off off, Pane manageOffPane) {
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

        offTable.getColumns().addAll(offID, initialDate, exposeDate, discountPercent);
        offTable.getItems().add(off);
        manageOffPane.getChildren().clear();
        manageOffPane.getChildren().add(offTable);
    }

    private static void addProduct(Good good_addProduct, Pane addProductPane, TextField addProductID_Label) {
        if (good_addProduct != null) addProduct_exsistingProduct(good_addProduct, addProductPane);
        else addProduct_newProduct(addProductPane, addProductID_Label);
    }

    private static void addProduct_newProduct(Pane addProductPane, TextField addProductID_Label) {
        addProductPane.getChildren().clear();
        TextField categoryTextField = new TextField("category name");
        Button setCategory = new Button("press to set category");
        VBox box = new VBox();
        box.setLayoutX(200);
        box.getChildren().addAll(categoryTextField, setCategory);
        addProductPane.getChildren().addAll(box);
        setCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Category category = CategoryController.getCategoryByName(categoryTextField.getText());
                HashMap<String, Object> input = new HashMap<>();
                input.put("categoryName", categoryTextField.getText());
                Client.sendMessage("get category by name", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    addProduct_getFields((Category) message.get("category"), addProductID_Label, box, addProductPane);
                } else if (message.get("status").equals("CategoryNotFindException")) {
                    showErrorAlert("there is no category with this name");
                }
            }
        });
    }

    private static void addProduct_getFields(Category category, TextField addProductID_Label, VBox box, Pane addProductPane) {
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
                HashMap<String, Object> input = new HashMap<>();
                input.put("goodId", addProductID_Label.getText().trim());
                input.put("goodName", goodName.getText().trim());
                input.put("companyName", companyName.getText().trim());
                input.put("price", Integer.parseInt(price.getText().trim()));
                input.put("explanatiopn", explanation.getText().trim());
                input.put("category", category);
                input.put("properties", properties);
                Client.sendMessage("add new product", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    showConfirmationAlert("edit product completed successfully");
                }
//                GoodController.getGoodController().AddProduct(addProductID_Label.getText().trim(), goodName.getText().trim()
//                        , companyName.getText().trim(), Integer.parseInt(price.getText().trim()),
//                        explanation.getText().trim(), properties, ((Seller) AccountController.loggedInUser)
//                        , category);

            }
        });
        box.getChildren().addAll(goodName, price, companyName, explanation, confirm);
        addProductPane.getChildren().clear();
        for (int j = 0; j < category.getSpecialProperties().size(); j++) {
            box.getChildren().add(textFieldProperties[j]);
        }
        addProductPane.getChildren().add(box);
    }

    private static void addProduct_exsistingProduct(Good good_addProduct, Pane addProductPane) {
        addProductPane.getChildren().clear();
        System.out.println("the id is already exist enter a price to add as its seller or 0 to ignore it:");
        TextField priceTextField = new TextField("enter new price");
        Button confirmPrice = new Button("confirm price");
        confirmPrice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int price = Integer.parseInt(priceTextField.getText().trim());
                if (price > 0) {
                    HashMap<String, Object> input = new HashMap<>();
                    input.put("price", price);
                    input.put("good", good_addProduct);
                    Client.sendMessage("add existing good", input);
                    Message message = Client.getMessage();
                    if (message.get("status").equals("successful")) {
                        showConfirmationAlert("you added this good to your selling goods");
                    }
                }
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(200);
        gridPane.setLayoutY(200);
        gridPane.setVgap(10);
        gridPane.add(priceTextField, 0, 0);
        gridPane.add(confirmPrice, 0, 1);
        addProductPane.getChildren().addAll(gridPane);
    }

    private static void editProduct(Pane manageProductPane, Good good) {
        TextField categoryTextField = new TextField("category name");
        Button setCategory = new Button("press to set category");
        VBox vBox = new VBox();
        vBox.setLayoutX(200);
        vBox.getChildren().clear();
        vBox.getChildren().addAll(categoryTextField, setCategory);
        manageProductPane.getChildren().clear();
        manageProductPane.getChildren().addAll(vBox);
        setCategory.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Category category = CategoryController.getCategoryByName(categoryTextField.getText());
                HashMap<String, Object> input = new HashMap<>();
                input.put("categoryName", categoryTextField.getText());
                Client.sendMessage("get category by name", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    editProduct_getFields((Category) message.get("category"), good, vBox, manageProductPane);
                } else if (message.get("status").equals("CategoryNotFindException")) {
                    showErrorAlert("there is no category with this name");
                }
            }
        });
    }

    private static void editProduct_getFields(Category category, Good good, VBox vBox, Pane manageProductPane) {
        TextField goodName = new TextField("good name");
        TextField price = new TextField("price");
        TextField companyName = new TextField("company name");
        TextField explanation = new TextField("explanation");
        TextField[] textFieldProperties = new TextField[category.getSpecialProperties().size()];
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
                HashMap<String, String> properties = new HashMap<>();
                for (String specialProperty : category.getSpecialProperties()) {
                    properties.put(specialProperty, textFieldProperties[i].getText().trim());
                    i++;
                }
                HashMap<String, Object> input = new HashMap<>();
                input.put("good", good);
                input.put("goodName", goodName.getText().trim());
                input.put("companyName", companyName.getText().trim());
                input.put("price", Integer.parseInt(price.getText().trim()));
                input.put("explanatiopn", explanation.getText().trim());
                input.put("category", category);
                input.put("properties", properties);
                Client.sendMessage("edit product", input);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    showConfirmationAlert("edit product completed successfully");
                }
//                GoodController.getGoodController().editProduct(good, goodName.getText().trim(), companyName.getText().trim(), Integer.parseInt(price.getText().trim())
//                        , ((Seller) AccountController.loggedInUser), explanation.getText().trim(), category, properties);
            }
        });
        vBox.getChildren().addAll(goodName, price, companyName, explanation, confirm);
        manageProductPane.getChildren().clear();
        for (int j = 0; j < category.getSpecialProperties().size(); j++) {
            vBox.getChildren().add(textFieldProperties[j]);
        }
        manageProductPane.getChildren().addAll(vBox);
    }

    private static void showConfirmationAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(text);
        alert.show();
    }

    private static void showErrorAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.show();
    }

    private static void makeProductTable(Pane manageProductPane, Good good) {
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

        productTable.getColumns().addAll(goodName, goodId, categoryName, explanations, point);
        productTable.setMinSize(600, 500);
        productTable.getItems().add(good);
        manageProductPane.getChildren().clear();
        manageProductPane.getChildren().add(productTable);
    }

    private static void makeBuyersTable(Pane manageProductPane, ArrayList<String> allBuyers) {
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
    }

    private static void editOff(Off off, Pane manageOffPane) {
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
                String inputString = off.getOffID() + " " + goods.getText().trim();
                inputString += " " + date1[0] + " " + date2[0] + " " + offPercent.getText().trim();
                HashMap<String, Object> inputs = new HashMap<>();
                inputs.put("inputString", inputString.trim());
                inputs.put("pattern", "(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)");
                Client.sendMessage("make edit off request", inputs);
                Message message = Client.getMessage();
                if (message.get("status").equals("successful")) {
                    showConfirmationAlert("you successfully made a request");
                } else if (message.get("status").equals("InvalidPatternException")) {
                    showErrorAlert("your pattern for input is wrong");
                }
//                    request = SellerController.makeRequest( inputString.trim(), "(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)");
//                    RequestController.addEditOffRequest(request, ((Seller) AccountController.loggedInUser));
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
        gridPane.add(goods, 0, 0);
        gridPane.add(firstDate, 0, 1);
        gridPane.add(lastDate, 0, 2);
        gridPane.add(offPercent, 0, 3);
        gridPane.add(confirm, 0, 4);
        manageOffPane.getChildren().clear();
        manageOffPane.getChildren().addAll(gridPane);
    }
}
