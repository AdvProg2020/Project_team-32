package Menus;

import Controller.RequestController;

import java.util.ArrayList;

public class DeclineRequestCommand extends Menu {
    public DeclineRequestCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter request number:");
    }

    @Override
    protected void execute() {
        int index = Integer.parseInt(scanner.nextLine());
        RequestController.removeRequest(index);
        System.out.println("Request declined successfully.");
        parentMenu.show();
        parentMenu.execute();
    }
}
