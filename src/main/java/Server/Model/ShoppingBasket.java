package Server.Model;

import java.io.Serializable;

public class ShoppingBasket implements Serializable {

    private static int numberOfShoppingBasketsCreatedYet = 0;


    private Good good;
    private Seller seller;
    private int quantity;
    private int id;

    public ShoppingBasket(Good good, Seller seller) {
        this.good = good;
        this.seller = seller;
        this.quantity = 1;
        numberOfShoppingBasketsCreatedYet += 1;
        this.id = numberOfShoppingBasketsCreatedYet;
    }

    public Good getGood() {
        return good;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }
}
