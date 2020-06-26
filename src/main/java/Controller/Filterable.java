package Controller;

import Model.Category;
import Model.Good;
import Model.Off;

import java.util.ArrayList;
import java.util.HashMap;

public class Filterable {

    protected static Category currentCategory = Category.rootCategory;
    protected static ArrayList<Good> selectedGoods = Good.getAllGoods();
    protected static HashMap<String , String> currentFilters = new HashMap<>();


    public static void filter(String filter, String value) throws Exception {
        ArrayList<Good> newSelectedGoods;
        if(filter.equalsIgnoreCase("category")){

            Category category = currentCategory.getSubcategory(value);
            currentCategory = category;
            newSelectedGoods = new ArrayList<Good>(category.getCategoryProduct());
            currentFilters.clear();
        } else {
            newSelectedGoods = new ArrayList<Good>();
            for (Good good : selectedGoods) {
                if(good.hasProperty(filter, value)){
                    newSelectedGoods.add(good);
                }
            }
        }
        selectedGoods = newSelectedGoods;
        currentFilters.put(filter, value);
    }

    public static void showCurrentFilters(){
        for (String s : currentFilters.keySet()) {
            System.out.println("Filter: " + s + "Value: " +currentFilters.get(s));
        }
    }

    public static void disableFilter(String filter) throws Exception{
        currentFilters.remove(filter);
        if(filter.equalsIgnoreCase("category")){
            currentCategory = currentCategory.getParentCategory();
            selectedGoods = new ArrayList<>(currentCategory.getCategoryProduct());
            currentFilters.clear();
            return;
        }
        for (String s : currentFilters.keySet()) {
            filter(s, currentFilters.get(s));
        }
        if(currentFilters.size() == 0) {
            setSelectedGoods(Good.getAllGoods());
        }

    }

    public static Category getCurrentCategory() {
        return currentCategory;
    }

    public static ArrayList<Good> getSelectedGoods() {
        return selectedGoods;
    }

    public static void reset(){
        currentCategory = Category.rootCategory;
        selectedGoods.clear();
        currentFilters.clear();
    }

    public static boolean isSortBy(String filter) {
        if (currentFilters.containsKey(filter)) {
            return true;
        } else {
            return false;
        }
    }

    //---------------------------------for test

    public static void setSelectedGoods(ArrayList<Good> selectedGoods) {
        Filterable.selectedGoods = selectedGoods;
    }
}
