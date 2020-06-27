package Controller;

import Controller.Exeptions.DiscountDoesNotExistException;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;
import Model.Boss;
import Model.Customer;
import Model.Discount;
import Model.Person;

import java.net.Inet4Address;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BossController {

    private static ArrayList<Discount> allDiscount = new ArrayList<Discount>();

    public static void createManager(String username, String password) throws DuplicateUsernameException {
        if (!Person.hasPersonByUserName(username)) {
            new Boss(username, password);
        } else {
            throw new DuplicateUsernameException();
        }
    }

    public static ArrayList<Discount> getAllDiscount() {
        return allDiscount;
    }

    public static void createDiscount(Date exposeDate, String discountId, int maxAmount, int percent, int numberOfUse, ArrayList<Customer> users) {
        Discount discount = new Discount(discountId, exposeDate, percent, maxAmount, numberOfUse);
        allDiscount.add(discount);
        System.out.println(users.size());
        for (Customer user : users) {
            user.addDiscount(discount, numberOfUse);
        }
    }

    public static Discount getDiscountById(String discountId) throws DiscountDoesNotExistException {
        for (Discount discount : allDiscount) {
            if (discount.getDiscountID().equals(discountId)) {
                return discount;
            }
        }
        throw new DiscountDoesNotExistException();
    }

    public static void editDiscount(Date exposeDate, String discountId, int maxAmount, int percent, Discount discount) {
        discount.setDiscountID(discountId);
        discount.setExposeDate(exposeDate);
        discount.setDiscountPercent(percent);
        discount.setMaxAmount(maxAmount);
    }

    public static void removeDiscount(Discount discount) {
        allDiscount.remove(discount);
        //TODO , should remove from costumer list
    }
}
