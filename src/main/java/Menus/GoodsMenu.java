package Menus;

import Model.Category;
import Model.Good;

import java.util.ArrayList;

public class GoodsMenu extends Menu {

    private static Category currentCategory;

    public GoodsMenu(String name, ArrayList<Menu> subMenu, Category rootCategory) {
        super(name, subMenu);
        currentCategory = rootCategory;
    }

    private void setCurrentCategory(Category category){
        this.currentCategory = category;
    }

    public static Category getCurrentCategory() {
        return currentCategory;
    }
}
