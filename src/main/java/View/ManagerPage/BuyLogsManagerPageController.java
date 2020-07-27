package View.ManagerPage;

import Server.Model.BuyLog;
import Server.Model.Message;
import View.Client;
import View.ManagerPage.GUIModels.BuyLogCard;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BuyLogsManagerPageController implements Initializable {
    public TableColumn<BuyLog, String> addressColumn;
    public TableColumn<BuyLog, String> statusColumn;
    public TableView<BuyLog> buyLogsTable;

    public void acceptBuyLog(ActionEvent actionEvent) {
        if(buyLogsTable.getSelectionModel().getSelectedItem() != null){
            HashMap<String , Object> inputs = new HashMap<>();
            inputs.put("buyLogId", buyLogsTable.getSelectionModel().getSelectedItem().getBuyLogId());
            Client.sendMessage("accept buyLog",inputs);
            Message serverAnswer = Client.getMessage();
            if(serverAnswer.get("status").equals("buyLog not find")){
                new Alert(Alert.AlertType.WARNING,"buyLog not find.").show();
            }
            updateTable();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a buyLog.").show();
        }
    }

    public void showBuyLog(ActionEvent actionEvent) {
        if(buyLogsTable.getSelectionModel().getSelectedItem() != null){
            Stage stageToShow = new Stage();
            stageToShow.setScene(new Scene(new BuyLogCard(buyLogsTable.getSelectionModel().getSelectedItem())));
            stageToShow.show();
        }
        else {
            new Alert(Alert.AlertType.WARNING,"Should select a buyLog.").show();
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));

        //initialize
        updateTable();

    }

    public void updateTable() {
        buyLogsTable.getItems().clear();
        buyLogsTable.getItems().addAll(getAllBuyLogs());
    }

    private ArrayList<BuyLog> getAllBuyLogs() {
        Client.sendMessage("get all buyLogs",new HashMap<>());
        return (ArrayList<BuyLog>) Client.getMessage().get("all buyLogs");
    }


}
