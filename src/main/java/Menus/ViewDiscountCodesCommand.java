package Menus;

import Model.Discount;

public class ViewDiscountCodesCommand extends Menu {
    public ViewDiscountCodesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Discount Codes";
    }

    @Override
    public void show() {
        for (Discount discount : Menu.getUserRecursively(this).getDiscounts()) {
            System.out.println(discount.toString());
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
