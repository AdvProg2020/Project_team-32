package Menus;

import java.util.ArrayList;

public class ShowAvailableSortsCommand extends Menu {

    public ShowAvailableSortsCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Show available sorts command";
    }

    @Override
    public void show() {
        System.out.println("Time");
        System.out.println("Point");
        System.out.println("Number of view");
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}

//subMenu of sorting