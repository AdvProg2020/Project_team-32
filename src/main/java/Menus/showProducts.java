package Menus;

import Controller.CustomerController;

import java.util.ArrayList;

public class showProducts extends  Menu {

    public showProducts(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
