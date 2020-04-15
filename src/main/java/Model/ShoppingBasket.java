package Model;

import java.util.HashMap;

public class ShoppingBasket {
    private HashMap<Good, Seller> shoppingBasket;

    public ShoppingBasket() {
        this.shoppingBasket = new HashMap<Good, Seller>();
    }

    public void selectingGood(Good good, Seller seller) {

    }
}
