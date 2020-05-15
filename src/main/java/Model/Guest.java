package Model;

import java.util.ArrayList;

public class Guest extends Person {
//    private ShoppingBasket shoppingBasket;
//
//    public Guest(ShoppingBasket shoppingBasket) {
//        super();
//        this.shoppingBasket = shoppingBasket;
//    }
//
//    public ShoppingBasket getShoppingBasket() {
//        return shoppingBasket;
//    }

    private ArrayList<ShoppingBasket> shoppingBaskets = new ArrayList<ShoppingBasket>();

    public Guest(ArrayList<ShoppingBasket> shoppingBaskets) {
        this.shoppingBaskets = shoppingBaskets;
    }

    public ArrayList<ShoppingBasket> getShoppingBaskets() {
        return shoppingBaskets;
    }

    public void setShoppingBaskets(ArrayList<ShoppingBasket> shoppingBaskets) {
        this.shoppingBaskets = shoppingBaskets;
    }
}
