package Model;

public class Guest extends Person {
    private ShoppingBasket shoppingBasket;

    public Guest(ShoppingBasket shoppingBasket) {
        super();
        this.shoppingBasket = shoppingBasket;
    }

}
