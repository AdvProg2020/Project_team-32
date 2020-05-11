package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {


    private String name;
    private HashMap<String, String> specialProperty; // key is property name and value is property amount
    private ArrayList<Category> subCategory = new ArrayList<Category>();
    private ArrayList<Good> allGoods;


    private static ArrayList<Category> allCategories = new ArrayList<Category>(); // by ali sharifi

    public Category(String name, HashMap<String, String> specialProperty) {
        this.name = name;
        this.specialProperty = specialProperty;
        this.allCategories.add(this);
    }

    public void addCategory(Category category) {
        this.subCategory.add(category);
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }
}
