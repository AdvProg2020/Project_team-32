package Menus;

import Model.Customer;

import java.util.ArrayList;

public class CustomerMenu extends Menu {

    private Customer user;
    //edited by ali sharifi
    //removing second argument from constructor
    public CustomerMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUser() {
        return user;
    }
}

//viewOrders is subMenu of this class -> ali sharifi
//viewBalanceCommand is subMenu of this menu -> ali sharifi

