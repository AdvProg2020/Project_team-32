package View.ManagerPage.GUIModels;

import Controller.BossController;
import Model.Boss;
import Model.Customer;
import Model.Discount;
import View.ManagerPage.AddEditDiscountPageController;
import View.ManagerPage.DiscountManagerPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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


    public void makeDiscount(LocalDate exposeDate, String discountId, String maxAmount, double percent, ArrayList<Customer> customers, String numberOfUse) {

        Date date = Date.from(exposeDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        int maxDiscountAmount = Integer.parseInt(maxAmount);
        int useNumber = Integer.parseInt(numberOfUse);
        int percentInt = (int) percent;

        if (!isEditPane) {
            BossController.createDiscount(date, discountId, maxDiscountAmount, percentInt, useNumber, customers);
        }
        else {
            BossController.editDiscount(date, discountId, maxDiscountAmount, percentInt, discountToChange);
        }

        parentController.closeStage();
        parentController.updateTable();

    }
}
