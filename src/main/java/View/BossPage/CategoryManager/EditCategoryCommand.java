package View.BossPage.CategoryManager;

import Controller.Exeptions.CategoryNotFindException;
import Controller.CategoryController;
import View.Menu;
import Model.Category;

import java.util.ArrayList;
import java.util.Collections;

public class EditCategoryCommand extends Menu {

    public EditCategoryCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "edit";
    }

    @Override
    public void show() {
        System.out.println("enter a category name to edit:");
    }

    @Override
    public void execute() {
        String categoryName = scanner.nextLine();
        try {
            Category category = CategoryController.getCategoryByName(categoryName);
            System.out.println("enter new name:");
            category.setName(scanner.nextLine());
            System.out.println("enter properties:(property1 property2 ...)");
            String[] properties = scanner.nextLine().split(" ");
            ArrayList<String> list = new ArrayList<>();
            Collections.addAll(list, properties);
            category.setSpecialProperties(list);
            System.out.println("category is changed successfully.");
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");
        }
        parentMenu.show();
        parentMenu.execute();
    }

}
