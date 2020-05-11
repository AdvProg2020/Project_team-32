package Controller;

import Model.BuyLog;
import Model.Customer;

public class CustomerController {

    public static BuyLog getBugLogWithId(String  id, Customer customer) throws Exception {
        for (BuyLog log : customer.getAllBuyLogs()) {
            if(log.getLogID().equals(id)){
                return log;
            }
        }
        throw new Exception();
    }

    public static void rateProduct(String goodId, Customer customer){

    }
}
