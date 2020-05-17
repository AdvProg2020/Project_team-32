package Menus;

import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.DuplicateCategoryException;
import Controller.CategoryController;

import java.util.ArrayList;
import java.util.Collections;

public class AddCategoryCommand extends Menu {

    public AddCategoryCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "add";
    }

    @Override
    protected void show() {
        System.out.println("enter a category name to add:");
    }

    @Override
    protected void execute() {
        try {
            String categoryName = scanner.nextLine();
            System.out.println("enter properties:(property1 property2 ...)");
            String[] properties = scanner.nextLine().split(" ");
            ArrayList<String> list = new ArrayList<>();
            Collections.addAll(list, properties);
            System.out.println("enter parent category name:");
            String parentName = scanner.nextLine();
            CategoryController.addCategory(categoryName, list, parentName);
            System.out.println("new category added successfully.");
        } catch (DuplicateCategoryException e) {
            System.out.println("this category is already made.");
        } catch (CategoryNotFindException e) {
            System.out.println("parent category does not exist.");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
