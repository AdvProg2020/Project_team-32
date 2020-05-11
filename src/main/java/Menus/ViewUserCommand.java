package Menus;

import Controller.AccountController;
import Model.Person;

import java.util.ArrayList;

public class ViewUserCommand extends Menu {
    public ViewUserCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please enter a username");
    }

    @Override
    protected void execute() {
        String username = scanner.nextLine();
        Person person = AccountController.getUser(username);
        System.out.println( "-------------------------------\n"
                + person.informationDisplay()
                + "\n-------------------------------");
        parentMenu.show();
        parentMenu.execute();
    }
}
