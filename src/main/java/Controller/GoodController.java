package Controller;

import Controller.Exeptions.GoodDoesNotExistException;
import Controller.Sort.SortByNumberOfView;
import Controller.Sort.SortByPoint;
import Model.Category;
import Model.Good;
import Model.Seller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GoodController {


    private static Category currentCategory;
    private static ArrayList<Good> selectedGoods;
    private static HashMap<String , String> currentFilters = new HashMap<>();
    private static int currentSort;

    public static void deleteGoodById(String Id) throws GoodDoesNotExistException {
        Good good = Good.getGoodById(Id);
        if(good == null){
            throw new  GoodDoesNotExistException();
        }
        Good.allGoods.remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }

    public static void setCurrentCategory(Category currentCategory) {
        GoodController.currentCategory = currentCategory;
    }

    public static Category getCurrentCategory() {
        return currentCategory;
    }

    public static void filter(final String filter, final String value){
        ArrayList<Good> newSelectedGoods = new ArrayList<Good>();
        for (Good good : selectedGoods) {
            if(good.hasProperty(filter, value)){
                newSelectedGoods.add(good);
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

        for (String s : currentFilters.keySet()) {
            filter(s, currentFilters.get(s));
        }

    }

    public static void sort(int sort) throws Exception {
        if(sort == 1){

        } else if(sort == 2){
            Collections.sort(selectedGoods, new SortByPoint());
        } else if(sort == 3){
            Collections.sort(selectedGoods, new SortByNumberOfView());
        } else {
            throw new Exception();
        }
    }

    public static int getCurrentSort() {
        return currentSort;
    }

    public static ArrayList<Good> getSelectedGoods() {
        return selectedGoods;
    }
}
