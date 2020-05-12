package Controller;

import Model.BuyLog;
import Model.Customer;
import Model.Good;

public class CustomerController {

    public static BuyLog getBugLogWithId(String  id, Customer customer) throws Exception {
        for (BuyLog log : customer.getAllBuyLogs()) {
            if(log.getLogID().equals(id)){
                return log;
            }
        }
        throw new Exception();
    }

    public static void rateProduct(String goodId ,int point , Customer customer) throws Exception {


        for (BuyLog log : customer.getAllBuyLogs()) {
            for (Good good : log.getGoodsBought()) {
                if(good.getGoodID().equals(goodId)){
                    good.setPoint(point);
                    return;
                }
            }
        }

        throw new Exception();
    }
}