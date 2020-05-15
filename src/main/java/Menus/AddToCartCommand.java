package Menus;

import Controller.IndividualGoodController;
import Model.Customer;
import Model.Person;

import java.util.ArrayList;

public class AddToCartCommand extends Menu {

    public AddToCartCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {

    }

    @Override
    protected void execute() {
        try {
            IndividualGoodController.addToCart(Menu.getUserRecursively(this));
        } catch (Exception e) {
            System.out.println("Seller is not selected");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
