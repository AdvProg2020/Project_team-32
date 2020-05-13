package Controller;

import Model.SellLog;
import Model.Seller;

import java.util.ArrayList;

public class SellerController {
    public static String viewProduct(Seller seller, int n){
        return seller.getSellingGoods().get(n).toString();
    }
    public static String viewProductBuyers(Seller seller, int n){
        String temp = null;
        String ID= seller.getSellingGoods().get(n).getGoodID();
        for (SellLog allSellingLog : seller.getAllSellingLogs()) {
            if(allSellingLog.getSoldGood().getGoodID().equals(ID)){
                temp+= seller.getAllSellingLogs().get(n).getBuyerUserNmae()+"\n";
            }
        }
        return  temp;
    }
    public static void editProducts(Seller seller, int n){


    }
}
