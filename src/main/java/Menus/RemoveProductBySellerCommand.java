package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

public class RemoveProductBySellerCommand extends Menu {

    public RemoveProductBySellerCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Remove Product";
    }

    @Override
    public void show() {
        System.out.println("enter number of product you want to remove:");
    }

    @Override
    public void execute() {
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
