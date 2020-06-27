package Controller;

import Controller.Exeptions.DuplicateGoodException;
import Controller.Exeptions.GoodDoesNotExistException;
import Controller.Sort.SortByNumberOfView;
import Controller.Sort.SortByPoint;
import Controller.Sort.SortType;
import Model.Category;
import Model.Good;
import Model.Person;
import Model.Seller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class GoodController extends Filterable{

    private static GoodController goodController;

    public static GoodController getGoodController() {
        if(goodController == null) {
            goodController = new GoodController();
        }
        return goodController;
    }

    private SortType currentSort;

    public void deleteGoodById(String Id) throws GoodDoesNotExistException {
        Good good = Good.getGoodById(Id);
        if(good == null){
            throw new GoodDoesNotExistException();
        }
        getGoodController().deleteGood(good);
    }

    public void deleteGood(Good good){
        Good.confirmedGoods.remove(good);
        Good.getAllGoods().remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }

    public static void setCurrentCategory(Category currentCategory) {
        GoodController.getGoodController().currentCategory = currentCategory;
    }

    public void sort(SortType type) throws Exception {
        if(type == SortType.sortByTime){

        } else if(type == SortType.sortByPoint){
            Collections.sort(selectedGoods, new SortByPoint());
        } else if(type == SortType.sortByNumberOfView){
            Collections.sort(selectedGoods, new SortByNumberOfView());
        } else {
            throw new Exception();
        }
    }

    public SortType getCurrentSort() {
        return currentSort;
    }

    public void AddProduct(String productId, String name, String companyName, int price, String explanation, HashMap<String, String> properties, Person seller,Category category) {
        new Good(name,productId,(Seller) seller,companyName,category,explanation,properties,price);
        RequestController.addProductRequest(productId,(Seller)seller);
    }

    public void editProduct(Good good, String name, String companyName, int price, Seller seller, String explanation, Category category, HashMap<String, String> properties) {
        new Good(name,good.getGoodID()+"edited",seller,companyName,category,explanation,properties,price);
        RequestController.addEditProductRequest(good.getGoodID(),seller);
        good.editingStatus();
    }

    @Override
    public void reset() {
        currentCategory = Category.rootCategory;
        selectedGoods.clear();
        currentFilters.clear();
    }

    public GoodController() {
        selectedGoods = Good.confirmedGoods;
        currentCategory = Category.rootCategory;
        currentFilters = new HashMap<>();
    }
}
