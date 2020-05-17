package Menus;

import Controller.RequestController;

public class DeclineRequestCommand extends Menu {

    public DeclineRequestCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "decline";
    }

    @Override
    public void show() {
        System.out.println("enter request number:");
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(scanner.nextLine());
        RequestController.removeRequest(index);
        System.out.println("Request declined successfully.");
        parentMenu.show();
        parentMenu.execute();
    }
}
