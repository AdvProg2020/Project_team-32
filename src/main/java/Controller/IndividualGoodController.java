package Controller;

import Model.Good;
import Model.Person;

public class IndividualGoodController {

    private static Good good;

    public static void setGoodId(String goodId) {
        good = Good.getGoodById(goodId);
    }

    public static void addToCart(Person person){
        
    }
}
