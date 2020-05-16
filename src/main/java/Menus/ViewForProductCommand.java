package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

import java.util.ArrayList;

public class
ViewForProductCommand extends Menu {
    public ViewForProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View individual Product";
    }

    @Override
    protected void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    protected void execute() {
        try {
            System.out.println(SellerController.viewProduct(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
        }
        catch(NumberOutOfBoundException e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
