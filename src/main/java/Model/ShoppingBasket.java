package Model;

import java.util.HashMap;

public class ShoppingBasket {
    private HashMap<Good,Seller> basketGoods;

    public ShoppingBasket(){
        this.basketGoods = new HashMap<Good, Seller>();
    }

    public ShoppingBasket(ShoppingBasket copyShoppingBasket) {
        this.basketGoods = new HashMap<Good, Seller>(copyShoppingBasket.basketGoods);
    }

    public HashMap<Good, Seller> getBasketGoods() {
        return basketGoods;
    }

    public void addGood(Good good){

    }
}
