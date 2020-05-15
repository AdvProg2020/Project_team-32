package Menus;

import Controller.Exeptions.CategoryNotFindException;
import Controller.Sort.CategoryController;
import Model.Category;

import java.util.ArrayList;

public class RemoveCategoryCommand extends Menu {
    public RemoveCategoryCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter a category name to remove:");
    }

    @Override
    protected void execute() {
        try {
            String categoryName = scanner.nextLine();
            CategoryController.removeCategory(CategoryController.getCategoryByName(categoryName));
            System.out.println("category deleted successfully.");
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");;
        }
    }
}
