package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class RemoveProductBySellerCommand extends Menu {

    public RemoveProductBySellerCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Remove Product";
    }

    @Override
    protected void show() {
        System.out.println("enter number of product you want to remove:");
    }

    @Override
    protected void execute() {
        try{
            SellerController.removeProduct(( (Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine()));
        }
        catch (NumberOutOfBoundException e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        System.out.println("product removed succesfully");
        parentMenu.show();
        parentMenu.execute();

    }
}
