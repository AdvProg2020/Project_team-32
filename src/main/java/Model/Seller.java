package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Person {
    private ArrayList<Good> sellingGoods = new ArrayList<Good>();
    private String factoryName;
    private ArrayList<Off> offs= new ArrayList<>();
    private ArrayList<SellLog> allSellingLogs;

    public ArrayList<SellLog> getAllSellingLogs() {
        return allSellingLogs;
    }

    public Seller(String userName, String password) {
        super(userName, password);
        this.allSellingLogs = new ArrayList<SellLog>();
    }

    public ArrayList<Off> getOffs() {
        return offs;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public ArrayList<Good> getSellingGoods() {
        return sellingGoods;
    }

    public Good getGoodByID(String goodID) {
        for (Good sellingGood : sellingGoods) {
            if (sellingGood.getGoodID().equals(goodID)) return sellingGood;
        }
        return null;
    }



    public String viewIndividualProduct(Good good) {
        return good.toString();
    }

    public void viewIndividualProductsBuyers(Good good) {

    }

    public void makeOff(Off off) {
        offs.add(off);
    }

    public void editOff(Off off) {

    }

    public void selling(Good good) {

    }

    public void makeGoodEditRequest(Good good) {

    }

    public void deleteGood(Good good) {
        sellingGoods.remove(good);
    }
}