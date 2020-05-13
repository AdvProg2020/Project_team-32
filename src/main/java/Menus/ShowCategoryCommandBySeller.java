package Menus;

import Controller.SellerController;
import Model.Category;
import Model.Seller;

import java.util.ArrayList;

public class ShowCategoryCommandBySeller extends  Menu {
    public ShowCategoryCommandBySeller(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {

    }

    @Override
    protected void execute() {
        ArrayList<Category> allCategories = SellerController.showCategory(( (Seller)getUserRecursively(this)));
        String toPrint =null;
        for (Category allCategory : allCategories) {
            toPrint+= allCategory.getName()+"\n";
        }
        System.out.println(toPrint);
        parentMenu.show();
        parentMenu.execute();
    }
}
