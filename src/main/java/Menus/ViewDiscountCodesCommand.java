package Menus;

import Model.Discount;

import java.util.ArrayList;

public class ViewDiscountCodesCommand extends Menu {
    public ViewDiscountCodesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Discount Codes";
    }

    @Override
    protected void show() {
        for (Discount discount : Menu.getUserRecursively(this).getDiscounts()) {
            System.out.println(discount.toString());
        }
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
