package Menus;

import Controller.BossController;
import Controller.Exeptions.DiscountDoesNotExistException;
import Model.Discount;

import java.util.ArrayList;

public class DiscountViewCommand extends Menu {
    public DiscountViewCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter a discountId to show its information:");
    }

    @Override
    protected void execute() {
        String discountId = scanner.nextLine();
        try {
            Discount discount = BossController.getDiscountById(discountId);
        } catch (DiscountDoesNotExistException exception) {
            System.out.println("discountId does not exist.");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
