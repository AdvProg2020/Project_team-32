package Server.Controller;

import Server.Model.Category;
import Server.Model.Good;

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

        System.out.println("------------- checking if filter exist ---------------------");
        System.out.println(currentFilters.keySet().contains(filter));
        System.out.println("-------------------------------------------------------------");

        currentFilters.remove(filter);
        if(filter.equalsIgnoreCase("category")){
            currentCategory = currentCategory.getParentCategory();
            selectedGoods = new ArrayList<>(currentCategory.getCategoryProduct());
            currentFilters.clear();
            return;
        }
        for (String s : currentFilters.keySet()) {
            System.out.println("---------------- in the current fileters after removing disabled filter ---------------");
            System.out.println(s);
            System.out.println("----------------------------------------------------------------------------------------");
            filter(s, currentFilters.get(s));
        }

        if(currentFilters.size() == 0) {
            reset();
        }

        System.out.println("------------- all confirm goods -------------------------");
        for (Good good : Good.confirmedGoods) {
            System.out.println(good);
        }
        System.out.println("----------------------------------------------------------");

        System.out.println("----------------- all goods after disabling filter -------------------");
        for (Good good : selectedGoods) {
            System.out.println(good);
        }
        System.out.println("-------------------------------------------------------------");
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
