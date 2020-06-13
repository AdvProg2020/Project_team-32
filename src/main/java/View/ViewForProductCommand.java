package View;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

public class
ViewForProductCommand extends Menu {
    public ViewForProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View individual Product";
    }

    @Override
    public void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    public void execute() {
        try {
            System.out.println(SellerController.viewProduct(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
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
