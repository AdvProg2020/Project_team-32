package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {

    private static ArrayList<Category> allCategories = new ArrayList<Category>();

    private String name;


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
    private static ArrayList<String> specialProperties;
    public Category(String name, ArrayList<String> specialProperty) {
        this.name = name;
        this.specialProperties = specialProperty;
        allCategories.add(this);
    }


    private ArrayList<Category> subCategory = new ArrayList<Category>();
    private ArrayList<Good> allGoods;
    /*
    public Category(String name, HashMap<String, String> specialProperty) {
        this.name = name;
        this.specialProperties = specialProperty;
        allCategories.add(this);
    }
    */
     */

    public void addCategory(Category category) {
        this.subCategory.add(category);
    }


    public static ArrayList<Category> getAllCategories() {
        return allCategories;
    }


}
