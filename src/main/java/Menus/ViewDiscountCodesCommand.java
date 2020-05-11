package Menus;

import Model.Discount;

import java.util.ArrayList;

public class ViewDiscountCodesCommand extends Menu{

    public ViewDiscountCodesCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (Discount discount : Menu.getUserRecursively(this).getDiscounts()) {
            System.out.println(discount);
        }
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
