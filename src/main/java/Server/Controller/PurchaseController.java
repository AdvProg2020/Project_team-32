package Server.Controller;

import Server.Controller.Exeptions.DiscountNotUsableException;
import Server.Controller.Exeptions.InvalidIDException;
import Server.Controller.Exeptions.NotLogedInException;
import Server.Model.*;

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
    public static float getDiscountPercent(String discountID, Customer customer)throws InvalidIDException , DiscountNotUsableException {
        for (Discount discount : customer.getDiscounts().keySet()) {
            if(discount.getDiscountID().equals(discountID) && discount.getUseCount()!=0 ){
                discount.setUseCount(discount.getUseCount()-1);
                return discount.getDiscountPercent();
            }else if(discount.getDiscountID().equals(discountID)){
                throw  new DiscountNotUsableException();
            }
        }throw new InvalidIDException();
    }
    public static float getPriceDiscounted(float finalPrice, float discountPercent){
        return finalPrice*((100-discountPercent)/100);
    }
    public static void payCommand(Customer customer, float finalPrice, float discountPercent, boolean isBank,String address,String phoneNumber){
        if (!isBank){
            customer.setCredit(customer.getCredit()-finalPrice);
        }

        for (ShoppingBasket shoppingBasket : customer.getShoppingBaskets()) {
            BuyLog buyLog =new BuyLog(Integer.toString(customer.getAllBuyLogs().size()),
                    new Date(),finalPrice,discountPercent,shoppingBasket.getGood(),
                    shoppingBasket.getSeller().getUserName() , "registered");
            customer.getAllBuyLogs().add(buyLog);

            //todo add this to boss buylogs

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
            shoppingBasket.getSeller().setCredit(shoppingBasket.getSeller().getCredit()+pricePaidToSeller *(100 - Boss.getWage())/100);
        }
        customer.getShoppingBaskets().clear();
    }
    public static void passTime(){
        ArrayList<Discount> toRemoveDiscount = new ArrayList<>();
        for (Discount discount : BossController.getAllDiscount()) {
            if(discount.getExposeDate().before(new Date())){
                for (Person allPerson : Person.allPersons) {
                    if(allPerson.getDiscounts().containsKey(discount)) {
                        allPerson.getDiscounts().remove(discount);
                    }
                }
                toRemoveDiscount.add(discount);
            }
        }
        BossController.getAllDiscount().removeAll(toRemoveDiscount);

        for (Person allPerson : Person.allPersons) {
            if(allPerson instanceof  Seller){
                ArrayList<Off> toRemoveOff = new ArrayList<>();
                for (Off off : ((Seller) allPerson).getOffs()) {
                    if(off.getExposeDate().before(new Date())){
                        toRemoveOff.add(off);
                    }
                }
                ((Seller)allPerson).getOffs().removeAll(toRemoveOff);
            }
        }

    }
}
