package Menus;

import java.util.ArrayList;

public class ViewProductInCard extends Menu {

    public ViewProductInCard(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please enter your product ID");

    }

    @Override
    protected void execute() {

        parentMenu.show();
        parentMenu.execute();
    }
}
