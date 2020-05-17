package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class CurrentFiltersCommand extends Menu {

    public CurrentFiltersCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Current filters command";
    }

    @Override
    public void show() {
        GoodController.showCurrentFilters();
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
