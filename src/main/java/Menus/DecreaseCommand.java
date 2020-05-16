package Menus;

import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;
import Model.Customer;
import Model.Good;

import java.util.ArrayList;

public class DecreaseCommand extends Menu {
    public DecreaseCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="DecreaseCommand";
    }

    @Override
    protected void show() {
        System.out.println("please enter your product ID");
    }

    @Override
    protected void execute() {
        try {
            int x= CustomerController.decrease(getUserRecursively(this ), scanner.nextLine());
            if(x==1) {
                System.out.println("product quantity decreased and deleted from basket");
            }
            else if((x==2)) {
                System.out.println(" just product quantity decreased");
            }
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
