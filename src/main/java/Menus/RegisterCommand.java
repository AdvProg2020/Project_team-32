package Menus;

import Controller.AccountController;
import Model.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterCommand extends Menu{

    public RegisterCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }


    @Override
    protected void show() {
        System.out.println("Please use this format to register:\ncreate account [type] [username] [password]");
    }

    @Override
    protected void execute() {
        String command = scanner.nextLine();
        if(commandValidation(command)){
            if(AccountController.canRegister(command.split(" "))){
                System.out.println("user registered successfully");
                parentMenu.show();
                parentMenu.execute();
            }
            else {
                System.out.println("can not register this user");
            }
        }
        else {
            this.show();
            this.execute();
        }
    }

    private boolean commandValidation(String command) {
        return command.matches("create account (\\S+) (\\S+) (\\S+)");
    }

}
