package Model;

import java.util.ArrayList;

public class Guest extends Person {

    private ArrayList<ShoppingBasket> shoppingBaskets;

    public Guest() {
        this.shoppingBaskets = new ArrayList<>();
    }
    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public void clearShoppingBaskets() {
        this.shoppingBaskets.clear();
    }
}
