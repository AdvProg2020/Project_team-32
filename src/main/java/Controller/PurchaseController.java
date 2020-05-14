package Controller;

import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.NotLogedInException;
import Model.*;

import java.util.ArrayList;
import java.util.Date;

public class PurchaseController {
    public static void isLogedIn(Person person)throws NotLogedInException {
        if(person instanceof Guest){
            throw new NotLogedInException();
        }
    }
    public static float calculatePrice(ArrayList<ShoppingBasket> shoppingBaskets){
        float totalPrice=0;
        boolean check = false ;
        for (ShoppingBasket shoppingBasket : shoppingBaskets) {
            check=false;
            for (Off off : shoppingBasket.getSeller().getOffs()) {
                for (Good good : off.getGoodsForOff()) {
                    if(good.getGoodID().equals(shoppingBasket.getGood().getGoodID())){
                        float onePrice=shoppingBasket.getGood().getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
                        totalPrice+= ((100 - off.getDiscountPercent())/100)*shoppingBasket.getQuantity()* onePrice;
                        check=true;
                        break;
                    }
                }
                if(check==true) break;
            }if(check ==false){
                float onePrice=shoppingBasket.getGood().getSellerAndPrices().get(shoppingBasket.getSeller().getUserName());
                totalPrice+= shoppingBasket.getQuantity()* onePrice;
            }
        }
        return totalPrice;
    }
    public static float getDiscountPercent(String discountID)throws InvalidIDException {
        for (Discount discount : BossController.getAllDiscount()) {
            if(discount.getDiscountID().equals(discountID)){
                return discount.getDiscountPercent();
            }
        }throw new InvalidIDException();

    }
    public static float getPriceDiscounted(float finalPrice, float discountPercent){
        return finalPrice*((100-discountPercent)/100);
    }
    public static void payCommand(Customer customer, float finalPrice, float discountPercent){
        customer.setCredit(customer.getCredit()-finalPrice);
        for (ShoppingBasket shoppingBasket : customer.getShoppingBaskets()) {
            customer.getAllBuyLogs().add(new BuyLog(Integer.toString(customer.getAllBuyLogs().size()),
                    new Date(),finalPrice,discountPercent,shoppingBasket.getGood(),
                    shoppingBasket.getSeller().getUserName() , "registered"));
            float offPercent = 0;
            for (Off off : shoppingBasket.getSeller().getOffs()) {
                for (Good good : off.getGoodsForOff()) {
                    if(good.getGoodID().equals(shoppingBasket.getGood().getGoodID())){
                        offPercent=off.getDiscountPercent();
                        break;
                    }
                }
            }
            float pricePaidToSeller= shoppingBasket.getGood().getSellerAndPrices().get(shoppingBasket.getSeller().getUserName())*(100-offPercent)/100;
            Seller seller = shoppingBasket.getSeller();
            seller.getAllSellingLogs().add(new SellLog(Integer.toString(seller.getAllSellingLogs().size()),new Date()
                    ,pricePaidToSeller,offPercent,shoppingBasket.getGood(),customer.getUserName(),"registered"));
            shoppingBasket.getSeller().setCredit(shoppingBasket.getSeller().getCredit()+pricePaidToSeller);
        }
        customer.getShoppingBaskets().clear();
    }
}
