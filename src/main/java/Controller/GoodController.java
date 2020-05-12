package Controller;

import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class GoodController {

    private ArrayList<Good> allGoods;


    public static void deleteGoodById(String Id){
        Good good = Good.getGoodById(Id);
        Good.allGoods.remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }


}
