package View.BossPage.DiscountManagerMenus;

import Controller.BossController;
import View.Menu;
import Model.Discount;

public class DiscountDisplayMenu extends Menu {

    public DiscountDisplayMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "view discount codes";
        subMenu.add(new DiscountViewCommand(this));
        subMenu.add(new DiscountEditCommand(this));
        subMenu.add(new DiscountRemoveCommand(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        for (Discount discount : BossController.getAllDiscount()) {
            System.out.println(discount.getDiscountID() + "\n-------------------");
        }
        super.show();
    }

}
