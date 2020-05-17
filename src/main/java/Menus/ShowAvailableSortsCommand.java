package Menus;

import java.util.ArrayList;

public class ShowAvailableSortsCommand extends Menu {

    public ShowAvailableSortsCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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