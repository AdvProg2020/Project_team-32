package Menus.BossPage.DiscountManagerMenus;

import Controller.BossController;
import Controller.Exeptions.DiscountDoesNotExistException;
import Menus.Menu;
import Model.Discount;

public class DiscountViewCommand extends Menu {

    public DiscountViewCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "view discount code";
    }

    @Override
    public void show() {
        System.out.println("enter a discountId to show its information:");
    }

    @Override
    public void execute() {
        String discountId = scanner.nextLine();
        try {
            Discount discount = BossController.getDiscountById(discountId);
            System.out.println(discount);
        } catch (DiscountDoesNotExistException exception) {
            System.out.println("discountId does not exist.");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
