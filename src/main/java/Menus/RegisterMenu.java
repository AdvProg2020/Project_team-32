package Menus;

import Model.Person;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegisterMenu extends Menu{

    public RegisterMenu(String name, ArrayList<Menu> subMenu) {
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
            if(canRegister(command.split(" "))){
                System.out.println("user registered successfully");
                // a menu should call here
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

    private boolean canRegister(String[] command) {
        return Person.register(command[3],command[2],command[4]);
    }

    private boolean commandValidation(String command) {
        return command.matches("create account (\\S+) (\\S+) (\\S+)");
    }
}
