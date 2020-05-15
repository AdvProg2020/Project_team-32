package Menus;

import Controller.Exeptions.CategoryNotFindException;
import Controller.Sort.CategoryController;
import Model.Category;

import java.util.ArrayList;
import java.util.Collections;

public class EditCategoryCommand extends Menu {
    public EditCategoryCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter a category name to edit:");
    }

    @Override
    protected void execute() {
        String categoryName = scanner.nextLine();
        try {
            Category category = CategoryController.getCategoryByName(categoryName);
            System.out.println("enter new name:");
            category.setName(scanner.nextLine());
            System.out.println("enter properties:(property1 property2 ...)");
            String[] properties = scanner.nextLine().split(" ");
            ArrayList<String> list = new ArrayList<String>();
            Collections.addAll(list, properties);
            category.setSpecialProperties(list);
            System.out.println("category is changed successfully.");
        } catch (CategoryNotFindException e) {
            System.out.println("can not find the category.");;
        }
        parentMenu.show();
        parentMenu.execute();
    }

}
