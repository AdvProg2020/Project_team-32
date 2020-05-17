package Menus;

import Controller.GoodController;
import Model.Good;

import java.util.ArrayList;

public class ShowProductsCommand extends Menu {

    public ShowProductsCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        for (Good good : GoodController.getSelectedGoods()) {
            System.out.println(good);
        }
    }
}

// this command shows all goods satisfying filters and category
