package Model;

import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<BuyLog> allBuyLogs;

    //private ArrayList<Logs> buyingLogs;

    //-------------------------------------- Ali Sharifi's changes
    private ShoppingBasket shoppingBasket;

    public Customer (String userName, String passWord) {
        super(userName, passWord);
        //----------------------------------- Ali Sharifi's changes
        this.shoppingBasket = new ShoppingBasket();
        //-----------------------------------

        this.allBuyLogs = new ArrayList<BuyLog>();
    }

    public void copyShoppingBasket(Customer guest){
        shoppingBasket = new ShoppingBasket(guest.shoppingBasket);
        guest.makeShoppingBasketEmpty();
    }

    private void makeShoppingBasketEmpty() {
        shoppingBasket = new ShoppingBasket();
    }

    public Customer(ShoppingBasket shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
        //----------------------------------- Ali Sharifi's changes
        this.shoppingBasket = shoppingBasket;
        //-----------------------------------
    }

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }
}
