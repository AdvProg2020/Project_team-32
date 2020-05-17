package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class DisableSortCommand extends Menu {

    public DisableSortCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Disable sort command";
    }

    @Override
    public void show() {

    }

    @Override
    public void execute() {
        try {
            GoodController.sort(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
