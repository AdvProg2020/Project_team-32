package Model;

import java.util.HashMap;

public class Person {
    private HashMap<Good,Salesman> shoppingBasket;

    public Person(HashMap<Good, Salesman> shoppingBasket) {
        this.shoppingBasket = shoppingBasket;
    }
}
