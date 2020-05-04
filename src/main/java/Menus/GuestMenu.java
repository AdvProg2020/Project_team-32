package Menus;



import Model.Customer;
import Model.Person;

import java.util.ArrayList;

public class GuestMenu extends Menu {

    private Customer guest;

    public GuestMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
        guest = new Customer("&&guest&&", "123");
    }

    public Customer getGuest() {
        return guest;
    }

}
