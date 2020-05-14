package Menus;

import Controller.BossController;
import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.NotLogedInException;
import Controller.PurchaseController;
import Model.*;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Date;

public class RecieverInformationCommand extends Menu {

    public RecieverInformationCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
    }

    @Override
    protected void execute() {
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
            if (answer == 1) {
                System.out.println("please enter your discount ID");
                discountID = scanner.nextLine();
                try {
                    discountPercent = PurchaseController.getDiscountPercent(discountID);
                    finalPrice = PurchaseController.getPriceDiscounted(finalPrice, discountPercent);
                } catch (InvalidIDException e) {
                    System.out.println("invalid ID");
                    this.show();
                    this.execute();
                }
            } else if (answer != 2) {
                this.show();
                this.execute();
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

