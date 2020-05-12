package Menus;

import Model.Seller;

import java.util.ArrayList;

public class SellerMenu extends Menu{

    private Seller user;

    public SellerMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {

        for(int i=0 ; i < subMenu.size() ; i++) {
            System.out.println("" + i + "." + subMenu.get(i));
        }
        System.out.println(""+ subMenu.size() + ".back");
        System.out.println("Please select a number:");
    }

    @Override
    protected void execute() {
        super.execute();
    }

    public void setUser(Seller user) {
        this.user = user;
    }

    public Seller getUser() {
        return user;
    }
}
