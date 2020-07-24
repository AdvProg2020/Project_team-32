package Server.Model;

import java.util.ArrayList;

public class Customer extends Person implements ShoppingBasketable{

    private ArrayList<BuyLog> allBuyLogs;

    private ArrayList<ShoppingBasket> shoppingBaskets;


    public Customer (String userName, String passWord) {
        super(userName, passWord);
        this.allBuyLogs = new ArrayList<BuyLog>();
        this.shoppingBaskets = new ArrayList<ShoppingBasket>();
    }

    @Override
    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public void setShoppingBaskets(ArrayList<ShoppingBasket> shoppingBaskets) {
        this.shoppingBaskets = shoppingBaskets;
    }

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
