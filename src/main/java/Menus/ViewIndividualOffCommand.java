package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

public class ViewIndividualOffCommand extends  Menu{
    public ViewIndividualOffCommand(Menu parentMenu) {
        super(parentMenu);
        this.name= "View individual Off";
    }

    @Override
    public void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    public void execute() {
        try{
            System.out.println(SellerController.showInddividualOff(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
        }
        catch (NumberOutOfBoundException e){
            System.out.println("number is out of bound");
            this.show();
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
