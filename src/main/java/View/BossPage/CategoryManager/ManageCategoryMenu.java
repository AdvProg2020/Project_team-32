package View.BossPage.CategoryManager;

import View.Menu;
import Model.Category;

public class ManageCategoryMenu extends Menu {

    public ManageCategoryMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage categories";
        subMenu.add(new EditCategoryCommand(this));
        subMenu.add(new AddCategoryCommand(this));
        subMenu.add(new RemoveCategoryCommand(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        for (Category category : Category.getAllCategories()) {
            System.out.println(category.getName());
        }
        super.show();
    }

}
