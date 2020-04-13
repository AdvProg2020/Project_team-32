package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Seller extends  Person{
    private ArrayList<Logs> sellingGoods;
    private String factoryName;
    public Seller(HashMap<Good, Seller> shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
    }
}