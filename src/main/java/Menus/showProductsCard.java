package Menus;

import Controller.CustomerController;

import java.util.ArrayList;

public class showProductsCard extends  Menu {

    public showProductsCard(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Product (Card)";
    }

    @Override
    protected void show() {
    }

    @Override
    protected void execute() {
        System.out.println(CustomerController.showProducts(getUserRecursively(this)));
        parentMenu.show();
        parentMenu.execute();
    }
}
