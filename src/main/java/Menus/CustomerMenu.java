package Menus;

import Model.Customer;

import java.util.ArrayList;

public class CustomerMenu extends Menu {

    private Customer user;
    //edited by ali sharifi
    //removing second argument from constructor
    public CustomerMenu(String name) {
        ArrayList<Menu> subMenu = new ArrayList<Menu>();
        subMenu.add(new Menu(){
            parentMenu = customerMenu;
            name = "view orders";
            //Overriding execute method
        });
        super(name, subMenu);
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUser() {
        return user;
    }
}
