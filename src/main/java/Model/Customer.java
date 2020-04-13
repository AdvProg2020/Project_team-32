package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Person {
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Logs> buyingLogs;

    //-------------------------------------- Ali Sharifi's changes
    private ShoppingBasket shoppingBasket;

    public Customer(HashMap<Good, Seller> shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
        //----------------------------------- Ali Sharifi's changes
        this.shoppingBasket = new ShoppingBasket(shoppingBasket);
        //-----------------------------------
    }
}
