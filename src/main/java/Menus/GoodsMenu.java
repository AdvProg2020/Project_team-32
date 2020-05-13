package Menus;

import Controller.GoodController;
import Model.Category;
import Model.Good;

import java.util.ArrayList;

public class GoodsMenu extends Menu {


    public GoodsMenu(String name, ArrayList<Menu> subMenu, Category rootCategory) {
        super(name, subMenu);
        GoodController.setCurrentCategory(rootCategory);
    }
    

}
