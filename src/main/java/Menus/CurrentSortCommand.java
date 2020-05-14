package Menus;

import Controller.GoodController;
import Model.Good;

import java.util.ArrayList;

public class CurrentSortCommand extends Menu {

    public CurrentSortCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println(SortCommand.getTypeOfSortWithInt(GoodController.getCurrentSort()));
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
