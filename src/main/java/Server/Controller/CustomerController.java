package Server.Controller;

import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.Exeptions.RateException;
import Server.Model.*;

// import org.graalvm.compiler.nodes.extended.OSRStartNode;


public class CustomerController {

    public static BuyLog getBugLogWithId(String id, Customer customer) throws InvalidIDException {
        for (BuyLog log : customer.getAllBuyLogs()) {
            if (log.getLogID().equals(id)) {
                return log;
            }
        }
        throw new InvalidIDException();
    }

    public static void rateProduct(String goodId, int point, Customer customer) throws RateException {
        for (BuyLog log : customer.getAllBuyLogs()) {
            if(log.getGoodsBought().getGoodID().equals(goodId) && point>0 && point<6){
                log.getGoodsBought().setPoint(point);
                return;
            }
        }
        throw new RateException();
    }

    public static String showProducts(Person person) {
        String outut = null;
        if (person instanceof Guest) {
            for (ShoppingBasket shoppingBasket : ((Guest) person).getShoppingBaskets()) {
                outut += shoppingBasket.getGood().getGoodID() + "\n";
            }
        } else if (person instanceof Customer) {
            for (ShoppingBasket shoppingBasket : ((Customer) person).getShoppingBaskets()) {
                outut += shoppingBasket.getGood().getGoodID() + "\n";
            }
        }return  null;


    }

    public static Good checkID(Person person, String ID) throws InvalidIDException {
        if (person instanceof Guest) {
            for (ShoppingBasket shoppingBasket : ((Guest) person).getShoppingBaskets()) {
                if (shoppingBasket.getGood().getGoodID().equals(ID))
                    return shoppingBasket.getGood();
            }
            throw new InvalidIDException();
        } else if (person instanceof Customer) {
            for (ShoppingBasket shoppingBasket : ((Customer) person).getShoppingBaskets()) {
                if (shoppingBasket.getGood().getGoodID().equals(ID))
                    return shoppingBasket.getGood();
            }
            throw new InvalidIDException();
        }
        return null;
    }
    public static void Increase(Person person,String ID)throws InvalidIDException{
        if(person instanceof Guest){
            for (ShoppingBasket shoppingBasket : ((Guest) person).getShoppingBaskets()) {
                if(shoppingBasket.getGood().getGoodID().equals(ID)){
                    shoppingBasket.setQuantity(shoppingBasket.getQuantity()+1);
                    return;
                }
            }throw new InvalidIDException();
        }else if( person instanceof Customer){

            for (ShoppingBasket shoppingBasket : ((Customer) person).getShoppingBaskets()) {
                if(shoppingBasket.getGood().getGoodID().equals(ID)){
                    shoppingBasket.setQuantity(shoppingBasket.getQuantity()+1);
                    return;
                }
            }throw new InvalidIDException();
        }
    }
    public static int decrease(Person person,String ID)throws InvalidIDException{
        ShoppingBasket shoppingBasketToRemove= null;
        int check=0;
        if(person instanceof Guest){
            for (ShoppingBasket shoppingBasket : ((Guest) person).getShoppingBaskets()) {
                if(shoppingBasket.getGood().getGoodID().equals(ID)){
                    shoppingBasket.setQuantity(shoppingBasket.getQuantity()-1);
                    check=1;
                    if(shoppingBasket.getQuantity()==0){
                        shoppingBasketToRemove=shoppingBasket;
                    }
                    break;
                }
            }
            if(check==1){
                if(shoppingBasketToRemove!=null){
                    ((Guest) person).getShoppingBaskets().remove(shoppingBasketToRemove);
                    return 1;
                }else return 2;
            }
            else throw new InvalidIDException();
        }else if( person instanceof Customer){
            for (ShoppingBasket shoppingBasket : ((Customer) person).getShoppingBaskets()) {
                if(shoppingBasket.getGood().getGoodID().equals(ID)){
                    shoppingBasket.setQuantity(shoppingBasket.getQuantity()-1);
                    check=1;
                    if(shoppingBasket.getQuantity()==0){
                        shoppingBasketToRemove=shoppingBasket;
                    }
                    break;
                }
            }
            if(check==1){
                if(shoppingBasketToRemove!=null){
                    ((Customer) person).getShoppingBaskets().remove(shoppingBasketToRemove);
                    return 1;
                }else return 2;
            }
            else throw new InvalidIDException();
        }
        return 0;
    }
    public static int showTotalPrices(Person person){
        int totalPrice=0;
        if(person instanceof Guest){
            for (ShoppingBasket shoppingBasket : ((Guest) person).getShoppingBaskets()) {
                totalPrice+= shoppingBasket.getGood().getSellerAndPrices().get(shoppingBasket.getSeller().getUserName()) * shoppingBasket.getQuantity();
            }
        }else if(person instanceof Customer){
            for (ShoppingBasket shoppingBasket : ((Customer) person).getShoppingBaskets()) {
                totalPrice+= shoppingBasket.getGood().getSellerAndPrices().get(shoppingBasket.getSeller().getUserName()) * shoppingBasket.getQuantity();
            }
        }
        return totalPrice;
    }
}