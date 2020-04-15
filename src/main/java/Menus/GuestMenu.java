package Menus;

import Model.Guest;

import java.util.ArrayList;

public class GuestMenu extends Menu {
    private Guest user;

    public GuestMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    public void setUser(Guest user) {
        this.user = user;
    }
}
