package Menus;

import Model.Category;

import java.util.ArrayList;

public class ManageCategoryMenu extends Menu {
    public ManageCategoryMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (Category category : Category.getAllCategories()) {
            System.out.println(category.getName());
        }
        super.show();
    }

}
