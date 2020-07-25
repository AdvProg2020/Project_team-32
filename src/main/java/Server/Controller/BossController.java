package Server.Controller;

import Server.Controller.Exeptions.DiscountDoesNotExistException;
import Server.Controller.Exeptions.DuplicateUsernameException;
import Server.Model.*;

import java.util.ArrayList;
import java.util.Date;

public class BossController {

    private static ArrayList<Discount> allDiscount = new ArrayList<Discount>();

    public static void createManager(String username, String password) throws DuplicateUsernameException {
        if (!Person.hasPersonByUserName(username)) {
            new Boss(username, password);
            Controller.setIsBossCreated(true);
        } else {
            throw new DuplicateUsernameException();
        }
    }

    public static ArrayList<Discount> getAllDiscount() {
        return allDiscount;
    }

    public static void createDiscount(Date exposeDate, String discountId, int maxAmount, int percent, int numberOfUse, ArrayList<Customer> users) {
        Discount discount = new Discount(discountId, exposeDate, percent, maxAmount, numberOfUse, users);
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

    public static void editDiscount(Date exposeDate, String discountId, int maxAmount, int percent, String curDiscountId) throws DiscountDoesNotExistException {
        Discount discount = getDiscountById(curDiscountId);
        discount.setDiscountID(discountId);
        discount.setExposeDate(exposeDate);
        discount.setDiscountPercent(percent);
        discount.setMaxAmount(maxAmount);
    }

    public static void removeDiscount(String discountId) throws DiscountDoesNotExistException {
        Discount discount = getDiscountById(discountId);
        allDiscount.remove(discount);
        for (Customer customer : discount.getUsers()) {
            customer.removeDiscount(discount);
        }
    }

    public static void createSupporter(String username, String password) throws DuplicateUsernameException {
        if (!Person.hasPersonByUserName(username)) {
            new Supporter(username, password);
        } else {
            throw new DuplicateUsernameException();
        }
    }

}
