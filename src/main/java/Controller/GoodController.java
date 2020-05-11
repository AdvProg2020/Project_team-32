package Controller;

import Model.Good;
import Model.Seller;

public class GoodController {
    public static void deleteGoodById(String Id){
        Good good = Good.getGoodById(Id);
        Good.allGoods.remove(good);
        for (Seller seller : good.getSellers()) {
            seller.deleteGood(good);
        }
    }
}
