package Menus;

import Controller.CustomerController;

public class ShowTotalPricesCommand extends Menu {
    public ShowTotalPricesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Total Prices";
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        System.out.println(CustomerController.showTotalPrices(getUserRecursively(this)));
        parentMenu.show();
        parentMenu.execute();
    }
}
