package Menus;

import Model.Boss;
import Model.Customer;
import Model.Person;


import java.util.ArrayList;

public class BossMenu extends Menu {
  
    private Boss user;

    public BossMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    public void setUser(Boss user) {
        this.user = user;
    }
}
