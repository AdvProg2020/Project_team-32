package Menus;

import Controller.AccountController;
import Controller.Exeptions.CanNotLoginException;
import Model.Boss;
import Model.Customer;
import Model.Person;
import Model.Seller;

import java.util.ArrayList;

public class LoginCommand extends Menu {
    public LoginCommand(String name) {
        super(name, null);
    }


    @Override
    protected void show() {
        System.out.println("Please use this format to register:\n login [username] [password]");
    }

    @Override
    protected void execute() {
        String command = scanner.nextLine();
        Person person;
        if (commandValidation(command)) {
            try {
                person = AccountController.login(command.split(" "));
                System.out.println("Login successfully done");
                Menu menu = AccountController.getRelativeMenuForLoginAndSetPerson(person);
                menu.show();
                menu.execute();
            } catch (CanNotLoginException exception) {
                System.out.println("username or password is not correct");
            }
        } else {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    private boolean commandValidation(String command) {
        return command.matches("login \\S+ \\S+");
    }

}
