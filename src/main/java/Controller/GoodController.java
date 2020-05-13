package Controller;

import Controller.Exeptions.GoodDoesNotExistException;
import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class GoodController {

    private ArrayList<Good> allGoods;


    public static void deleteGoodById(String Id) throws GoodDoesNotExistException {
        Good good = Good.getGoodById(Id);
        if(good == null){
            throw new  GoodDoesNotExistException();
        }
        Good.allGoods.remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }


}
