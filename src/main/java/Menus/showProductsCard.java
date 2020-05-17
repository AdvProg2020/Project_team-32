package Menus;

import Controller.CustomerController;

public class showProductsCard extends  Menu {

    public showProductsCard(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Product (Card)";
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        System.out.println(CustomerController.showProducts(getUserRecursively(this)));
        parentMenu.show();
        parentMenu.execute();
    }
}
