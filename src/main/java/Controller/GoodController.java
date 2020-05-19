package Controller;

import Controller.Exeptions.DuplicateGoodException;
import Controller.Exeptions.GoodDoesNotExistException;
import Controller.Sort.SortByNumberOfView;
import Controller.Sort.SortByPoint;
import Model.Category;
import Model.Good;
import Model.Person;
import Model.Seller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GoodController extends Filterable{

    /*private static Category currentCategory = Category.rootCategory;
    private static ArrayList<Good> selectedGoods;
    private static HashMap<String , String> currentFilters = new HashMap<>();*/

    private static int currentSort;

    public static void deleteGoodById(String Id) throws GoodDoesNotExistException {
        Good good = Good.getGoodById(Id);
        if(good == null){
            throw new GoodDoesNotExistException();
        }
        deleteGood(good);
    }

    public static void deleteGood(Good good){
        Good.confirmedGoods.remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }

    public static void setCurrentCategory(Category currentCategory) {
        GoodController.currentCategory = currentCategory;
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



    public static void AddProduct(String productId, String name, String companyName, int price, String explanation, HashMap<String, String> properties, Person seller,Category category) {
        new Good(name,productId,(Seller) seller,companyName,category,explanation,properties,price);
        RequestController.addProductRequest(productId,(Seller)seller);
    }

    public static void editProduct(Good good, String name, String companyName, int price, Seller seller, String explanation, Category category, HashMap<String, String> properties) {
        new Good(name,good.getGoodID()+"edited",seller,companyName,category,explanation,properties,price);
        RequestController.addEditProductRequest(good.getGoodID()+ " ",seller);
        good.editingStatus();
    }

}
