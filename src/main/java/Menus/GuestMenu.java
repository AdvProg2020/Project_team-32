package Menus;

import Model.Customer;
import Model.Person;
import Model.Guest;


import java.util.ArrayList;

public class GuestMenu extends Menu {
    private Guest user;

    public GuestMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "guest menu";
        user = new Guest();
    }

    public Guest getUser() {
        return user;
    }
}
