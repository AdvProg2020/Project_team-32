package Controller;

import Model.Category;
import Model.Good;
import Model.Off;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Filterable {

    protected Category currentCategory;
    protected ArrayList<Good> selectedGoods;
    protected HashMap<String , String> currentFilters;


    public void filter(String filter, String value) throws Exception {
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

    public void showCurrentFilters(){
        for (String s : currentFilters.keySet()) {
            System.out.println("Filter: " + s + "Value: " +currentFilters.get(s));
        }
    }

    public void disableFilter(String filter) throws Exception{
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

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public ArrayList<Good> getSelectedGoods() {
        return selectedGoods;
    }

    public abstract void reset();

    public boolean isSortBy(String filter) {
        if (currentFilters.containsKey(filter)) {
            return true;
        } else {
            return false;
        }
    }

    //---------------------------------for test

    public void setSelectedGoods(ArrayList<Good> selectedGoods) {
        selectedGoods = selectedGoods;
    }
}
