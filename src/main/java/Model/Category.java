package Model;

import javafx.scene.control.TreeItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Category implements Serializable {

    private static ArrayList<Category> allCategories = new ArrayList<>();
    public static Category rootCategory = new Category("mainCategory", null , null);
    private String name;

    //private

    private Category parentCategory;

    private static ArrayList<String> generalProperties = new ArrayList<>();
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
        if(parentCategory != null) {
            parentCategory.addSubCategory(this);
        }
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

        ArrayList categoryProducts = new ArrayList<>(this.categoryProduct);
        for (Category category : subCategory) {
            for (Good good : category.getCategoryProduct()) {
                categoryProducts.add(good);
            }
        }
        return categoryProducts;
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

    public String getParentCategoryName() {
        return parentCategory==null ? "null" : parentCategory.name;
    }

    public TreeItem<String> getCategory() {
        TreeItem<String> category = new TreeItem<>();
        if(subCategory.size() == 0) {
            return null;
        }
        for (Category c : subCategory) {
            TreeItem a = new TreeItem(c.getName());
            if(c.getCategory() != null) {
                a.getChildren().add(c.getCategory());
            }
            category.getChildren().add(a);
        }
        return category;
    }

    public static Category getCategoryByName(String name) {
        for (Category category : allCategories) {
            if(category.getName().equalsIgnoreCase(name)) {
                return category;
            }
        }
        return null;
    }
}