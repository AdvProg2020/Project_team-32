package View;

import Controller.PurchaseController;
import Model.Discount;

public class ViewDiscountCodesCommand extends Menu {
    public ViewDiscountCodesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Discount Codes";
    }

    @Override
    public void show() {
        PurchaseController.passTime();
        for (Discount discount : getUserRecursively(this).getDiscounts().keySet()) {
            System.out.println(discount.toString());
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
