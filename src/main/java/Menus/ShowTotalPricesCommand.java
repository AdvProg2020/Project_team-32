package Menus;

import Controller.CustomerController;

import java.util.ArrayList;

public class ShowTotalPricesCommand extends Menu {

    public ShowTotalPricesCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
