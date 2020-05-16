package Menus;

import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class ManageProductsMenu extends Menu {

    public ManageProductsMenu(Menu parentMenu) {
        super(parentMenu);
    }

    @Override
    protected void show() {
        int i=1;
        for (Good sellingGood : ((Seller) getUserRecursively(this)).getSellingGoods()) {
            System.out.println(i+": "+sellingGood.getName()+" / ID:" +sellingGood.getGoodID());
            i++;
        }
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
