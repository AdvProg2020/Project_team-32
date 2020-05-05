package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends Person {
    private ArrayList<Good> sellingGoods = new ArrayList<Good>();
    private String factoryName;

    public Seller(HashMap<Good, Seller> shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
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

    public void makeOff() {

    }

    public void editOff(Off off) {

    }

    public void selling(Good good) {

    }

    public void makeGoodEditRequest(Good good) {

    }

}