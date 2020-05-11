package Menus;

import Model.Category;

import java.util.ArrayList;

public class ViewCategoriesCommand extends Menu{

    public ViewCategoriesCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (Category category : Category.getAllCategories()) {
            System.out.println(category);
        }
    }

    @Override
    protected void execute() {
        super.show();
        super.execute();
    }
}
