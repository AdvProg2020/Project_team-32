package View.SellerPage;

import Controller.AccountController;
import Model.Customer;
import Model.Good;
import Model.SellLog;
import Model.Seller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

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














    }
}
