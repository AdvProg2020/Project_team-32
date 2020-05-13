package Menus;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;

import java.util.ArrayList;

public class DeleteUserCommand extends Menu {
    public DeleteUserCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Please enter a username:");
    }

    @Override
    protected void execute() {
        String username = scanner.nextLine();
        try {
            AccountController.deleteUser(username);
            System.out.println("user is deleted successfully");
        } catch (UserDoesNotExistException exception) {
            System.out.println("username is not correct");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
