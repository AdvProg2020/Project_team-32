package View.ManagerPage.GUIModels;

import Server.Controller.BossController;
import Server.Model.Customer;
import Server.Model.Discount;
import Server.Model.Message;
import View.Client;
import View.ManagerPage.AddEditDiscountPageController;
import View.ManagerPage.DiscountManagerPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ChangeDiscountPane extends Pane {

    DiscountManagerPageController parentController;
    AddEditDiscountPageController controller;
    boolean isEditPane;
    Discount discountToChange;

    public ChangeDiscountPane(DiscountManagerPageController parentController, Discount discount) {

        URL url = null;
        try {
            url = new File("src\\main\\resources\\GUIFiles\\add-edit-discount-page.fxml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FXMLLoader loader = new FXMLLoader(url);

        //set main scene
        try {
            Parent parent = loader.load();
            this.controller = loader.getController();
            getChildren().add(parent);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //set controllers
        this.parentController = parentController;


        //check if discount is not null
        if (discount != null) {
            setDiscountAmount(discount);
        }

        //set page controller field
        controller.setChangeDiscountPane(this);

        isEditPane = discount != null;

        discountToChange = discount;

    }

    private void setDiscountAmount(Discount discount) {

        //set discount amount
        controller.percentAmountLabel.setText("" + discount.getDiscountPercent() + "%");
        controller.percentAmountPicker.setValue(discount.getDiscountPercent());

        controller.idInput.setText(discount.getDiscountID());
        controller.idInput.setDisable(true);


        controller.datePicker.setValue(Instant.ofEpochMilli(discount.getExposeDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        controller.maxAmountInput.setText("" + discount.getMaxAmount());

        controller.numberOfUseInput.setText("" + discount.getUseCount());

    }


    public void makeDiscount(LocalDate exposeDate, String discountId, String maxAmount, double percent, ArrayList<String> customers, String numberOfUse) {

        Date date = Date.from(exposeDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int maxDiscountAmount = Integer.parseInt(maxAmount);
        int useNumber = Integer.parseInt(numberOfUse);
        int percentInt = (int) percent;

        if (!isEditPane) {
            createDiscount(date, discountId, maxDiscountAmount, percentInt, useNumber, customers);
        }
        else {
            editDiscount(date, discountId, maxDiscountAmount, percentInt, discountToChange);
        }

        parentController.closeStage();
        parentController.updateTable();

    }

    private void editDiscount(Date date, String discountId, int maxDiscountAmount, int percentInt, Discount discountToChange) {
        HashMap<String , Object> inputs = new HashMap<>();
        inputs.put("date", date);
        inputs.put("discountId", discountId);
        inputs.put("maxDiscountAmount", maxDiscountAmount);
        inputs.put("percentInt", percentInt);
        inputs.put("curDiscountId", discountToChange.getDiscountID());
        Client.sendMessage("edit discount", inputs);
        Message serverAnswer = Client.getMessage();
        if(!serverAnswer.get("status").equals("successful")){
            new Alert(Alert.AlertType.WARNING, "discount does not exist.").show();
        }
    }

    private void createDiscount(Date date, String discountId, int maxDiscountAmount, int percentInt, int useNumber, ArrayList<String> customers) {
        HashMap<String , Object> inputs = new HashMap<>();
        inputs.put("date", date);
        inputs.put("discountId", discountId);
        inputs.put("maxDiscountAmount", maxDiscountAmount);
        inputs.put("percentInt", percentInt);
        inputs.put("useNumber", useNumber);
        inputs.put("customers", customers);
        Client.sendMessage("create discount", inputs);
        Client.getMessage();
    }
}
