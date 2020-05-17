package Menus;

import Controller.BossController;
import Controller.Exeptions.DiscountDoesNotExistException;
import Model.Discount;

public class DiscountRemoveCommand extends Menu {

    public DiscountRemoveCommand(Menu parentMenu) {
        super(parentMenu);
        this.name= "remove discount code";
    }

    @Override
    public void show() {
        System.out.println("enter a discountId to remove:");
    }

    @Override
    public void execute() {
        String discountId = scanner.nextLine();
        try {
            Discount discount = BossController.getDiscountById(discountId);
            BossController.removeDiscount(discount);
        } catch (DiscountDoesNotExistException exception) {
            System.out.println("discountId does not exist.");
        }
    }

}
