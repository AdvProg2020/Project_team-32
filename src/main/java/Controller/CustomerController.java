package Controller;

import Controller.Exeptions.InvalidID;
import Controller.Exeptions.InvalidIDException;
import Model.*;

import java.util.ArrayList;

public class CustomerController {

    public static BuyLog getBugLogWithId(String id, Customer customer) throws Exception {
        for (BuyLog log : customer.getAllBuyLogs()) {
            if (log.getLogID().equals(id)) {
                return log;
            }
        }
        throw new Exception();
    }

    public static void rateProduct(String goodId, int point, Customer customer) throws Exception {


        for (BuyLog log : customer.getAllBuyLogs()) {
            for (Good good : log.getGoodsBought()) {
                if (good.getGoodID().equals(goodId)) {
                    good.setPoint(point);
                    return;
                }
            }
        }

        throw new Exception();
    }

    public static String showProducts(Person person) {
        String outut = null;
        if (person instanceof Guest) {
            for (Good good : ((Guest) person).getShoppingBasket().getBasketGoods().keySet()) {
                outut += good.getGoodID() + "\n";
            }
        } else if (person instanceof Customer) {
            for (Good good : ((Customer) person).getShoppingBasket().getBasketGoods().keySet()) {
                outut += good.getGoodID() + "\n";
            }
        }

    }

    public static Good checkID(Person person, String ID) throws InvalidIDException {
        if (person instanceof Guest) {
            ArrayList<Good> s = new ArrayList<>(((Guest) person).getShoppingBasket().getBasketGoods().keySet());
            for (Good good : s) {
                if (good.getGoodID().equals(ID)) {
                    return good;
                }
            }
            throw new InvalidIDException();
        } else if (person instanceof Customer) {
            ArrayList<Good> s = new ArrayList<>(((Customer) person).getShoppingBasket().getBasketGoods().keySet());
            for (Good good : s) {
                if (good.getGoodID().equals(ID)) {
                    return good;
                }
            }
            throw new InvalidIDException();
        }
    }
}