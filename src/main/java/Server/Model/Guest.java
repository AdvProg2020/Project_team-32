package Server.Model;

import java.util.ArrayList;

public class Guest extends Person implements ShoppingBasketable {

    private ArrayList<ShoppingBasket> shoppingBaskets;

    public Guest() {
        this.shoppingBaskets = new ArrayList<>();
    }

    @Override
    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public void clearShoppingBaskets() {
        this.shoppingBaskets.clear();
    }
}
