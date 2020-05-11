package Menus;

import java.util.ArrayList;

public class RateCommand extends Menu {


    public RateCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Please enter a rate between 1 and 5");
    }

    @Override
    protected void execute() {

    }
}
