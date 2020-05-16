package Menus;

import Controller.BossController;
import Model.Discount;

import java.util.ArrayList;

public class DiscountDisplayMenu extends Menu {

    public DiscountDisplayMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "view discount codes";
        subMenu.add(new DiscountViewCommand(this));
        subMenu.add(new DiscountEditCommand(this));
        subMenu.add(new DiscountRemoveCommand(this));
    }

    @Override
    protected void show() {
        for (Discount discount : BossController.getAllDiscount()) {
            System.out.println(discount.getDiscountID() + "\n-------------------");
        }
        super.show();
    }

}
