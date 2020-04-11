package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Category {
    private String name;
    private HashMap<String , String > specialProperty; // key is property name and value is property amount
    private Category subCategory;
    private ArrayList<Good> allGoods;

    public Category(String name, HashMap<String, String> specialProperty) {
        this.name = name;
        this.specialProperty = specialProperty;
    }
}
