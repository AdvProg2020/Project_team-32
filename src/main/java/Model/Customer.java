package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Customer extends Person {
    private ArrayList<BuyLog> allBuyLogs;
    private ArrayList<Logs> buyingLogs;

    //-------------------------------------- Ali Sharifi's changes
    private ShoppingBasket shoppingBasket;

    public Customer (String userName, String passWord) {
        super(userName, passWord);
        //----------------------------------- Ali Sharifi's changes
        this.shoppingBasket = new ShoppingBasket();
        //-----------------------------------
    }
}
