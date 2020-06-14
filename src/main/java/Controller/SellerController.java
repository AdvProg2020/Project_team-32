package Controller;

import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Controller.Exeptions.NumberOutOfBoundException;
import Model.*;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.StringReader;
import java.nio.file.InvalidPathException;
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
        ArrayList<String> temp = null;
        for (Good sellingGood : seller.getSellingGoods()) {
            if(sellingGood.getGoodID().equals(ID)){
                for (SellLog allSellingLog : seller.getAllSellingLogs()) {
                    if(allSellingLog.getSoldGood().equals(sellingGood)){
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

    public static void addProduct() {

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
            return request+" "+input;
        }
        else throw new InvalidPatternException();
    }

}
