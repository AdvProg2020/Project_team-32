package Menus;

import Controller.SellerController;
import Model.Good;
import Model.Seller;

import java.util.ArrayList;

public class RemoveProductBySellerCommand extends Menu {

    public RemoveProductBySellerCommand(String name , ArrayList<Menu> subMenu){
        super(name, subMenu);
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
        catch (Exception e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        System.out.println("product removed succesfully");
        parentMenu.show();
        parentMenu.execute();

    }
}
