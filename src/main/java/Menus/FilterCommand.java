package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class FilterCommand extends Menu {

    public FilterCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("Enter an available filter with its value");
    }

    @Override
    public void execute() {
        String filter = scanner.nextLine();
        String value = scanner.nextLine();
        GoodController.filter(filter, value);
        parentMenu.show();
        parentMenu.execute();
    }
}
