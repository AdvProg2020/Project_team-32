package Menus;

import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;
import Model.Good;

import java.util.ArrayList;

public class ViewProductInCard extends Menu {

    public ViewProductInCard(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("please enter your product ID");

    }

    @Override
    public void execute() {
        try {
           Good good =  CustomerController.checkID(getUserRecursively(this),scanner.nextLine());
            //call good page menu
        }
        catch (InvalidIDException e){
            System.out.println("ID is incorrect");
            this.show();
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
