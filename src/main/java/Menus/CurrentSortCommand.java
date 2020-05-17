package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class CurrentSortCommand extends Menu {

    public CurrentSortCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println(SortCommand.getTypeOfSortWithInt(GoodController.getCurrentSort()));
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
