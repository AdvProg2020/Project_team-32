package Controller;

import Controller.Exeptions.DiscountDoesNotExistException;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Model.Boss;
import Model.Discount;
import Model.Person;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BossController {

    private static ArrayList<Discount> allDiscount = new ArrayList<Discount>();

    public static void createManager(String[] command) throws DuplicateUsernameException {
        if(!Person.hasPersonByUserName(command[1])){
            new Boss(command[1], command[2]);
        }
        else{
            throw new DuplicateUsernameException();
        }
    }

    public static ArrayList<Discount> getAllDiscount() {
        return allDiscount;
    }

    public static void createDiscount(String[] command) {
        String[] exposeDateString = command[1].split(",");
        Date exposeDate = new Date(Integer.parseInt(exposeDateString[0]),Integer.parseInt(exposeDateString[1]),Integer.parseInt(exposeDateString[2]));
        allDiscount.add(new Discount(command[0], exposeDate, Integer.parseInt(command[2]), Integer.parseInt(command[3])));
    }

    public static Discount getDiscountById(String discountId) throws DiscountDoesNotExistException {
        for (Discount discount : allDiscount) {
            if(discount.getDiscountID().equals(discountId)){
                return discount;
            }
        }
        throw new DiscountDoesNotExistException();
    }
}
