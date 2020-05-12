package Menus;

import Controller.AccountController;
import Controller.Exeptions.WrongPasswordException;
import Controller.Exeptions.UserDontExistException;
import Model.Person;

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
            } catch (WrongPasswordException exception) {
                System.out.println("username or password is not correct");
            } catch (UserDontExistException exception) {
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
