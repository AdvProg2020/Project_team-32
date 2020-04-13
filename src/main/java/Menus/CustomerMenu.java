package Menus;

import Model.Customer;

import java.util.ArrayList;

public class CustomerMenu extends Menu {

    private Customer user;

    public CustomerMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    public void setUser(Customer user) {
        this.user = user;
    }

}
