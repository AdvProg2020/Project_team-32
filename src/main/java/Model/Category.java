package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {

    public static Category rootCategory = new Category("mainCategory", null , null);
    private static ArrayList<Category> allCategories = new ArrayList<>();
    private String name;

    private Category parentCategory;

    private static ArrayList<String> generalProperties;
    static {
        generalProperties.add("name");
        generalProperties.add("price");
        generalProperties.add("seller");
    }

    private ArrayList<String> specialProperties;
    public Category(String name, ArrayList<String> specialProperty, Category parentCategory) {
        this.name = name;
        this.specialProperties = specialProperty;
        this.parentCategory = parentCategory;
        this.subCategory = new ArrayList<>();
        this.categoryProduct = new ArrayList<>();
        allCategories.add(this);
    }

    private ArrayList<Category> subCategory;
    private ArrayList<Good> categoryProduct;

    public String getName() {
        return name;
    }

    public void addProduct(Good good){
        this.categoryProduct.add(good);
    }

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

    public Category getSubcategory(String name) throws Exception {
        for (Category category : subCategory) {
            if(category.name.equalsIgnoreCase(name)){
                return category;
            }
        }
        throw new Exception();
    }

    public Category getParentCategory() {
        return parentCategory;
    }
}