package Server.Controller;

import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.Exeptions.InvalidPatternException;
import Server.Controller.Exeptions.NumberOutOfBoundException;
import Server.Model.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SellerController {
    public static Good viewProduct(Seller seller, String ID) throws InvalidIDException {
        for (Good sellingGood :seller.getSellingGoods()) {
            if (sellingGood.getGoodID().equals(ID)){
                return sellingGood;
            }
        }
        throw new InvalidIDException();
    }

    public static ArrayList<String> viewProductBuyers(Seller seller,String ID) throws InvalidIDException {
        ArrayList<String> temp = new ArrayList<>();
        for (Good sellingGood : seller.getSellingGoods()) {
            if(sellingGood.getGoodID().equals(ID)){
                for (SellLog allSellingLog : seller.getAllSellingLogs()) {
                    if(allSellingLog.getSoldGood().getGoodID().equals(sellingGood.getGoodID())){
                        temp.add(allSellingLog.getBuyerUserNmae());
                    }
                }
                return temp;
            }
        }throw new InvalidIDException();
    }

    public static void editProducts(Seller seller, int n, int price) throws NumberOutOfBoundException {
        if (seller.getSellingGoods().size() >= n - 1) {
            seller.getSellingGoods().get(n - 1).getSellerAndPrices().replace(seller.getUserName(), price);
        }
        throw new NumberOutOfBoundException();

    }


    public static void removeProduct(Seller seller, String ID) throws InvalidIDException {
        Good good = null;
        for (Good sellingGood : seller.getSellingGoods()) {
            if(sellingGood.getGoodID().equals(ID)){
                sellingGood.getSellers().remove(seller);
                sellingGood.getSellerAndPrices().remove(seller.getUserName());
                good=sellingGood;
                break;
            }
        }
        if(good!=null) seller.getSellingGoods().remove(good);
        else throw new InvalidIDException();
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
    public static Off showInddividualOff(Seller seller , String ID) throws InvalidIDException{
        for (Off off : seller.getOffs()) {
            if(off.getOffID().equals(ID)){
                return off;
            }
        }throw new InvalidIDException();

    }
    public static Off checkOffID(ArrayList<Off> offs , String offID) throws InvalidIDException {
        for (Off off : offs) {
            if(off.getOffID().equals(offID)) return off;
        }throw new InvalidIDException();
    }
    public static Good getGoodFromSellingGood(Seller seller , String goodID) throws InvalidIDException{
        ArrayList <Good> goods = seller.getSellingGoods();
        for (Good good : goods) {
            if(good.getGoodID().equals(goodID)) return good;
        }throw new InvalidIDException();
    }
    public static String makeRequest(String request ,String input , String pattern) throws InvalidPatternException{
        if(Pattern.compile(pattern).matcher(input).matches()) {
            return input;
        }
        else throw new InvalidPatternException();
    }

}
