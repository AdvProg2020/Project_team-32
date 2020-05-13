package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class CurrentFiltersCommand extends Menu {

    public CurrentFiltersCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        GoodController.showCurrentFilters();
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
