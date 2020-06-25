package Controller;

import Controller.Exeptions.UserDoesNotExistException;
import Model.*;

public class IndividualGoodController {

    private static Good good;
    private static Seller seller;

    public static void setGoodId(String goodId) {
        good = Good.getGoodById(goodId);
    }

    public static void addToCart(Person person) throws Exception {

        if(seller == null){
            throw new Exception();
        }

        ShoppingBasket shoppingBasket = new ShoppingBasket(good, seller);

        if(person instanceof Customer){
            ((Customer) person).getShoppingBaskets().add(shoppingBasket);
        } else {
            ((Guest) person).getShoppingBaskets().add(shoppingBasket);
        }
    }

    public static void selectSeller(String seller1) throws UserDoesNotExistException {
        if(good.hasSeller(seller1)){
            seller = (Seller)Person.getPersonByUserName(seller1);
        }
        //something TODO by ali sharifi
    }

    public static Good getGood() {
        return good;
    }

    public static Seller getSeller() {
        return seller;
    }
}
