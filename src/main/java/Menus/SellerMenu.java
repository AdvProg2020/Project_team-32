package Menus;

import Model.Seller;

import java.util.ArrayList;

public class SellerMenu extends Menu{

    private Seller user;

    public SellerMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    public void setUser(Seller user) {
        this.user = user;
    }

    public Seller getUser() {
        return user;
    }
}
