package Menus;

import Controller.AccountController;

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
        AccountController.deleteUser(username);
        System.out.println("user is deleted successfully");
        parentMenu.show();
        parentMenu.execute();
    }
}
