package Menus;

import Controller.AccountController;
import Controller.Exeptions.DuplicateBossException;
import Controller.Exeptions.DuplicateUsernameException;

import java.util.ArrayList;

public class RegisterCommand extends Menu {

    public RegisterCommand(Menu parentMenu) {
        super(parentMenu);
    }


    @Override
    public void show() {
        System.out.println("Please use this format to register:\ncreate account [manager|costumer|seller] [username] [password]");
    }

    @Override
    public void execute() {
        String command = scanner.nextLine();
        if (commandValidation(command)) {
            try {
                AccountController.register(command.split(" ")[3], command.split(" ")[2], command.split(" ")[4]);
                System.out.println("user registered successfully");
            } catch (DuplicateUsernameException exception) {
                System.out.println("this username is already exist");
            } catch (DuplicateBossException exception) {
                System.out.println("boss is already registered");
            }
            parentMenu.show();
            parentMenu.execute();
        } else {
            System.out.println("invalid command.");
            this.show();
            this.execute();
        }
    }

    private boolean commandValidation(String command) {
        return command.matches("create account (manager|costumer|seller) (\\S+) (\\S+)");
    }

}
