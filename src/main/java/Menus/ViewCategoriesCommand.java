package Menus;

import Model.Category;

import java.util.ArrayList;

public class ViewCategoriesCommand extends Menu{


    public ViewCategoriesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "View categories command";
    }

    @Override
    public void show() {
        for (Category category : Category.getAllCategories()) {
            System.out.println(category);
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
//subMenu of goodsMenu