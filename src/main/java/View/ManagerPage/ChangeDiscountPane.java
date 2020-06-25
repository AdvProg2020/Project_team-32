package View.ManagerPage;

import Model.Discount;
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

public class ChangeDiscountPane extends Pane {

    DiscountManagerPageController parentController;
    AddEditDiscountPageController controller;


    public ChangeDiscountPane(DiscountManagerPageController parentController,Discount discount) {

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
        if(discount != null ){
            setDiscountAmount(discount);
        }

        System.out.println(controller);
        System.out.println(parentController);

    }

    private void setDiscountAmount(Discount discount){

        //set discount amount

        controller.percentAmountLabel.setText("" + discount.getDiscountPercent() + "%");
        controller.percentAmountPicker.setValue(discount.getDiscountPercent());

        controller.idInput.setText(discount.getDiscountID());
        controller.idInput.setDisable(true);


        controller.datePicker.setValue(Instant.ofEpochMilli(discount.getExposeDate().getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate());

        controller.maxAmountInput.setText("" + discount.getMaxAmount());

    }


}
