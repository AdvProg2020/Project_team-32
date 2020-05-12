package Menus;

import Controller.BossController;
import Model.Discount;

import java.util.ArrayList;

public class DiscountDisplayMenu extends Menu {
    public DiscountDisplayMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (Discount discount : BossController.getAllDiscount()) {
            System.out.println(discount.getDiscountID() + "\n-------------------");
        }
        super.show();
    }

}
