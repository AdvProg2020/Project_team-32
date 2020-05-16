package Menus;

import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;
import Model.Customer;

import java.util.ArrayList;

public class ShowOrderCommand extends Menu {
    public ShowOrderCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Show Individual Command";
    }

    @Override
    protected void show() {
        System.out.println("Please enter orderIde");
    }

    @Override
    protected void execute() {

        try{
            System.out.println(CustomerController.getBugLogWithId(scanner.nextLine(), (Customer) getUserRecursively(this)));
        }
        catch (InvalidIDException e){
            System.out.println("Invalid ID");
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
