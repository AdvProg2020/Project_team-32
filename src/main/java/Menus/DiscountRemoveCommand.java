package Menus;

import Controller.BossController;
import Controller.Exeptions.DiscountDoesNotExistException;
import Model.Boss;
import Model.Discount;

import java.io.BufferedOutputStream;
import java.util.ArrayList;

public class DiscountRemoveCommand extends Menu {

    public DiscountRemoveCommand(Menu parentMenu) {
        super(parentMenu);
        this.name= "remove discount code";
    }

    @Override
    protected void show() {
        System.out.println("enter a discountId to remove:");
    }

    @Override
    protected void execute() {
        String discountId = scanner.nextLine();
        try {
            Discount discount = BossController.getDiscountById(discountId);
            BossController.removeDiscount(discount);
        } catch (DiscountDoesNotExistException exception) {
            System.out.println("discountId does not exist.");
        }
    }

}
