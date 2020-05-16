package Menus;

import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;
import Model.Customer;
import Model.Good;

import java.util.ArrayList;

public class IncreaseCommand extends  Menu {
    public IncreaseCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Increase Command";
    }

    @Override
    protected void show() {
        System.out.println("please enter your product ID");
    }

    @Override
    protected void execute() {
        try {
            CustomerController.Increase(getUserRecursively(this),scanner.nextLine());
            System.out.println("produuct quantity increased");
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
}
