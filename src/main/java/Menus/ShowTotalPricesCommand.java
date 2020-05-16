package Menus;

import Controller.CustomerController;

import java.util.ArrayList;

public class ShowTotalPricesCommand extends Menu {
    public ShowTotalPricesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Total Prices";
    }

    @Override
    protected void show() {
    }

    @Override
    protected void execute() {
        System.out.println(CustomerController.showTotalPrices(getUserRecursively(this)));
        parentMenu.show();
        parentMenu.execute();
    }
}
