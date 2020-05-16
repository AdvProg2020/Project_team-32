package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

import java.util.ArrayList;

public class ViewBuyersForProducts extends Menu {
    public ViewBuyersForProducts(Menu parentMenu) {
        super(parentMenu);
        this.name= "View Buyers";
    }

    @Override
    protected void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    protected void execute() {
        try {
            System.out.println(SellerController.viewProductBuyers(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
        }
        catch(NumberOutOfBoundException e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
