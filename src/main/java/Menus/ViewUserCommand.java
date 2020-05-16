package Menus;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;
import Menus.Menu;
import Model.Person;

import java.util.ArrayList;

public class ViewUserCommand extends Menu {

    public ViewUserCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "view user";
    }

    @Override
    protected void show() {
        System.out.println("please enter a username");
    }

    @Override
    protected void execute() {
        String username = scanner.nextLine();
        try {
            Person person = AccountController.getUser(username);
            System.out.println("-------------------------------\n"
                    + person.informationDisplay()
                    + "\n-------------------------------");
        } catch (UserDoesNotExistException exception) {
            System.out.println("username is not correct");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
