package Model;

import java.util.ArrayList;

public class Customer extends Person {

    private ArrayList<BuyLog> allBuyLogs;

    //private ArrayList<Logs> buyingLogs;

    //-------------------------------------- Ali Sharifi's changes


    private ArrayList<ShoppingBasket> shoppingBaskets = new ArrayList<ShoppingBasket>();
    public Customer (String userName, String passWord) {
        super(userName, passWord);
        this.allBuyLogs = new ArrayList<BuyLog>();
    }
/*
    public void copyShoppingBasket(Customer guest){
        shoppingBasket = new ShoppingBasket(guest.shoppingBasket);
        guest.makeShoppingBasketEmpty();
    }
*/
    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public void setShoppingBaskets(ArrayList<ShoppingBasket> shoppingBaskets) {
        this.shoppingBaskets = shoppingBaskets;
    }
/*
    private void makeShoppingBasketEmpty() {
        shoppingBasket = new ShoppingBasket();
    }
*/
    public Customer(ShoppingBasket shoppingBasket, String userName, String firstName, String lastName, String phoneID, String eMail, String passWord, int credit) {
        super(userName, firstName, lastName, phoneID, eMail, passWord, credit);
        //----------------------------------- Ali Sharifi's changes
        this.shoppingBasket = shoppingBasket;
        //-----------------------------------
    }
    /*
    public ShoppingBasket getShoppingBasket() {
        return shoppingBasket;
    }*/

    public ArrayList<BuyLog> getAllBuyLogs() {
        return allBuyLogs;
    }


    public boolean boughtGood(Good good){
        for (BuyLog log : allBuyLogs) {
            if(log.getGoodsBought().equals(good)){
                return true;
            }
        }
        return false;
    }
}
