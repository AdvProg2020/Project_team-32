package Menus;

import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class ManageProducts extends Menu {
    public ManageProducts(String name , ArrayList<Menu> subMenu){
        super(name, subMenu);
    }

    @Override
    protected void show() {
        int i=1;
        for (Good sellingGood : ((Seller) getUserRecursively(this)).getSellingGoods()) {
            System.out.println(i+": "+sellingGood.getName()+" / ID:" +sellingGood.getGoodID());
            i++;
        }

        for( i=0 ; i < subMenu.size() ; i++) {
            System.out.println("" + i + "." + subMenu.get(i));
        }
        System.out.println(""+ subMenu.size() + ".Back");
        System.out.println("Please select a number:");
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
