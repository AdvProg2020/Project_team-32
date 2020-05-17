package Menus;

import Controller.SellerController;
import Model.Category;
import Model.Seller;

import java.util.ArrayList;

public class ShowCategoryCommandBySeller extends  Menu {
    public ShowCategoryCommandBySeller(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Categories (Seller)";
    }

    @Override
    public void show() {

    }

    @Override
    public void execute() {
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
