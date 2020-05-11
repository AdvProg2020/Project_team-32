package Menus;

import Controller.CustomerController;
import Model.Customer;

import java.util.ArrayList;

public class ShowOrderCommand extends Menu {

    public ShowOrderCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
        catch (Exception e){
            System.out.println("buy log with this id doesn't exist.");
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
