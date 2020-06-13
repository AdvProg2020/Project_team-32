package View;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

public class ViewBuyersForProducts extends Menu {
    public ViewBuyersForProducts(Menu parentMenu) {
        super(parentMenu);
        this.name= "View Buyers";
    }

    @Override
    public void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    public void execute() {
        try {
            System.out.println(SellerController.viewProductBuyers(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
        }
        catch(NumberOutOfBoundException e){
            System.out.println("number is not acceptable ");
            parentMenu.show();
            parentMenu.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
