package Controller;

import Controller.BossController;
import Controller.Exeptions.CategoryNotFindException;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateCategoryException;
import Controller.GoodController;
import Model.Category;
import Model.Good;

import java.util.ArrayList;

public class CategoryController {

    public static Category getCategoryByName(String name) throws CategoryNotFindException {
        for (Category category : Category.getAllCategories()) {
            if(category.getName().equals(name)){
                return category;
            }
        }
        throw new CategoryNotFindException();
    }

    public static void addCategory(String categoryName, ArrayList<String> list, String parentName) throws CategoryNotFindException, DuplicateCategoryException {
        Category parentCategory = getCategoryByName(parentName);
        if(hasCategoryByName(categoryName))
            throw new DuplicateCategoryException();
       new Category(categoryName, list, parentCategory);
    }

    private static boolean hasCategoryByName(String name) {
        for (Category category : Category.getAllCategories()) {
            if(category.getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public static void removeCategory(Category category){
        for (Good good : category.getCategoryProduct()) {
            GoodController.getGoodController().deleteGood(good);
        }
        for (Category subCategory : category.getSubCategory()) {
            removeCategory(subCategory);
        }
        Category.getAllCategories().remove(category);
    }

    public static void editCategory(Category categoryToChange, String name, ArrayList<String> properties) {
        categoryToChange.setName(name);
        categoryToChange.setSpecialProperties(properties);
    }
}
