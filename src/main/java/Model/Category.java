package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {

    public static Category rootCategory = new Category("mainCategory", null , null);
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private String name;
    private Category parentCategory;

    /*
    //----------------------------------------------------------------------------
    // by ali sharifi
    private static HashMap<String , String> generalProperties = new HashMap<String, String>();
    //----------------------------------------------------------------------------
    private HashMap<String, String> specialProperties; // key is property name and value is property amount
     */

    private static ArrayList<String> generalProperties;
    static {
        generalProperties.add("name");
        generalProperties.add("category");
        generalProperties.add("price");
        generalProperties.add("seller");
    }
    private ArrayList<String> specialProperties;
    public Category(String name, ArrayList<String> specialProperty, Category parentCategory) {
        this.name = name;
        this.specialProperties = specialProperty;
        this.parentCategory = parentCategory;
        allCategories.add(this);
    }

    private ArrayList<Category> subCategory = new ArrayList<>();
    private ArrayList<Good> categoryProduct;

    public String getName() {
        return name;
    }
    /*
    public Category(String name, HashMap<String, String> specialProperty) {
        this.name = name;
        this.specialProperties = specialProperty;
        allCategories.add(this);
    }
    */

    public void addSubCategory(Category category) {
        this.subCategory.add(category);
    }

    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }

    public static ArrayList<String> getGeneralProperties() {
        return generalProperties;
    }

    public ArrayList<String> getSpecialProperties() {
        return specialProperties;
    }

    public ArrayList<Good> getCategoryProduct() {
        return categoryProduct;
    }

    public ArrayList<Category> getSubCategory() {
        return subCategory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecialProperties(ArrayList<String> specialProperties) {
        this.specialProperties = specialProperties;
    }
}