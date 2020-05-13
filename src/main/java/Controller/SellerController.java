package Controller;

import Controller.Exeptions.NumberOutOfBoundException;
import Model.Category;
import Model.Good;
import Model.SellLog;
import Model.Seller;

import java.util.ArrayList;

public class SellerController {
    public static String viewProduct(Seller seller, int n) throws NumberOutOfBoundException {
        if (seller.getSellingGoods().size() >= n - 1)
            return seller.getSellingGoods().get(n - 1).toString();
        throw new NumberOutOfBoundException();
    }

    public static String viewProductBuyers(Seller seller, int n) throws NumberOutOfBoundException {
        String temp = null;
        if (seller.getSellingGoods().size() >= n - 1) {
            String ID = seller.getSellingGoods().get(n).getGoodID();
            for (SellLog allSellingLog : seller.getAllSellingLogs()) {
                if (allSellingLog.getSoldGood().getGoodID().equals(ID)) {
                    temp += seller.getAllSellingLogs().get(n - 1).getBuyerUserNmae() + "\n";
                }
            }
            return temp;
        }
        throw new NumberOutOfBoundException();
    }

    public static void editProducts(Seller seller, int n, int price) throws NumberOutOfBoundException {
        if (seller.getSellingGoods().size() >= n - 1) {
            seller.getSellingGoods().get(n - 1).getSellerAndPrices().replace(seller.getUserName(), price);
        }
        throw new NumberOutOfBoundException();

    }

    public static void addProduct() {

    }

    public static void removeProduct(Seller seller, int n) throws NumberOutOfBoundException {
        if (seller.getSellingGoods().size() >= n - 1) {
            seller.getSellingGoods().get(n - 1).getSellers().remove(seller);
            seller.getSellingGoods().get(n - 1).getSellerAndPrices().remove(seller.getUserName());
        } else throw new NumberOutOfBoundException();
    }

    public static ArrayList<Category> showCategory(Seller seller) {
        ArrayList<Category> categories = new ArrayList<>();
        for (Good sellingGood : seller.getSellingGoods()) {
            int check = 1;
            for (Category category : categories) {
                if (category.getName().equals(sellingGood.getName())) {
                    check = 0;
                    break;
                }
            }
            if (check == 1) categories.add(sellingGood.getCategory());
        }
        return categories;
    }
    public static String showInddividualOff(Seller seller , int n) throws NumberOutOfBoundException{
        if (seller.getOffs().size() >= n - 1) {
            return seller.getOffs().get(n).toString();
        }throw new NumberOutOfBoundException();

    }
    public static int addGoodToOff( Se){

    }
}
