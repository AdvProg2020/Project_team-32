package Menus;

import Model.Customer;
import Model.Person;
import Model.Guest;


import java.util.ArrayList;

public class GuestMenu extends Menu {
    private Guest user;

    private Customer guest;

    public GuestMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
        guest = new Customer("&&guest&&", "123");
    }

    public Customer getGuest() {
        return guest;
    }

    public void setUser(Guest user) {
        this.user = user;
    }

}
