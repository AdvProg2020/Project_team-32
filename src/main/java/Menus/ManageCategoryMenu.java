package Menus;

import Model.Category;

import java.util.ArrayList;

public class ManageCategoryMenu extends Menu {

    public ManageCategoryMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage categories";
        subMenu.add(new EditCategoryCommand(this));
        subMenu.add(new AddCategoryCommand(this));
        subMenu.add(new RemoveCategoryCommand(this));
    }

    @Override
    protected void show() {
        for (Category category : Category.getAllCategories()) {
            System.out.println(category.getName());
        }
        super.show();
    }

}
