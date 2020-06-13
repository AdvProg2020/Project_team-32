package View;

import Controller.CustomerController;
import Controller.Exeptions.InvalidIDException;

public class IncreaseCommand extends  Menu {
    public IncreaseCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Increase Command";
    }

    @Override
    public void show() {
        System.out.println("please enter your product ID");
    }

    @Override
    public void execute() {
        try {
            CustomerController.Increase(getUserRecursively(this),scanner.nextLine());
            System.out.println("produuct quantity increased");
        }
        catch (InvalidIDException e){
            System.out.println("ID is incorrect");
            parentMenu.show();
            parentMenu.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
