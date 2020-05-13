package Controller;

import Controller.Exeptions.GoodDoesNotExistException;
import Model.Category;
import Model.Good;
import Model.Seller;

import java.util.ArrayList;
import java.util.HashMap;

public class GoodController {


    private static Category currentCategory;
    private static ArrayList<Good> selectedGoods;
    private static HashMap<String , String> currentFilters = new HashMap<>();

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
}
