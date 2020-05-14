package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class DisableSortCommand extends Menu {

    public DisableSortCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {

    }

    @Override
    protected void execute() {
        try {
            GoodController.sort(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
