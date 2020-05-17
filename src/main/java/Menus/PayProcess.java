package Menus;

import Controller.BossController;
import Controller.Exeptions.DiscountNotUsableException;
import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.NotLogedInException;
import Controller.PurchaseController;
import Model.*;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class PayProcess extends Menu {
    public PayProcess(Menu parentMenu) {
        super(parentMenu);
        this.name = "Pay Process";
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        try {
            PurchaseController.isLogedIn(getUserRecursively(this));
            Customer customer = ((Customer) getUserRecursively(this));
            String address, phoneNumber, discountID;
            float finalPrice = 0, discountPercent = 0;
            System.out.println("please enter your address");
            address = scanner.nextLine();
            System.out.println("please enter your phone number");
            phoneNumber = scanner.nextLine();
            finalPrice = PurchaseController.calculatePrice((customer).getShoppingBaskets());
            System.out.println("do you have a discount 1.yes 2.no");
            int answer = Integer.parseInt(scanner.nextLine());
            int random = ((new Random()).nextInt(100));
            if (answer == 1) {
                System.out.println("please enter your discount ID");
                discountID = scanner.nextLine();
                try {
                    discountPercent = PurchaseController.getDiscountPercent(discountID, customer);
                    finalPrice = PurchaseController.getPriceDiscounted(finalPrice, discountPercent);
                } catch (InvalidIDException e) {
                    System.out.println("invalid ID");
                    parentMenu.show();
                    parentMenu.execute();
                } catch (DiscountNotUsableException e) {
                    System.out.println("you cant use this discount anymore");
                    parentMenu.show();
                    parentMenu.execute();

                }
            } else if (answer == 2) {
                if (finalPrice >= 1000000) {
                    discountPercent = 10;
                    finalPrice = PurchaseController.getPriceDiscounted(finalPrice, discountPercent);
                    System.out.println("you get 10% discount because you bought over 1000000 ");
                } else if ((random % 10) == 0) {
                    discountPercent = (random % 20);
                    finalPrice = PurchaseController.getPriceDiscounted(finalPrice, discountPercent);
                    System.out.println("you are lucky : you got " + discountPercent + "percent discount");
                }
            } else {
                parentMenu.show();
                parentMenu.execute();
            }
            if (finalPrice <= (customer).getCredit()) {
                PurchaseController.payCommand(customer, finalPrice, discountPercent);
                System.out.println("you have succesfully bought your shopping Basket");

            } else {
                System.out.println("you do not have enough money");
                parentMenu.show();
                parentMenu.execute();
            }
        } catch (NotLogedInException e) {
            System.out.println("you are a guest please enter as a customer");
            parentMenu.show();
            parentMenu.execute();
        }

    }
}

