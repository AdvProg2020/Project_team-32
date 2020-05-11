package Menus;

import Controller.AccountController;
import Model.Boss;
import Model.Customer;
import Model.Person;
import Model.Seller;

import java.util.ArrayList;

public class LoginCommand extends Menu{
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
        if(commandValidation(command)){
            if((person = AccountController.login(command.split(" ")))!=null){
                System.out.println("Login successfully done");
                Menu menu = AccountController.getRelativeMenuForLogin(person);
                menu.show();
                menu.execute();
            }
            else {
                System.out.println("username or password is not correct");
            }
        }
        else {
            parentMenu.show();
            parentMenu.execute();
        }
    }

    private boolean commandValidation(String command) {
        return command.matches("login \\S+ \\S+");
    }

}
