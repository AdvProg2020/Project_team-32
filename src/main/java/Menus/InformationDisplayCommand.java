package Menus;

import java.util.ArrayList;

public class InformationDisplayCommand extends Menu {
    public InformationDisplayCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
    }

    @Override
    protected void execute() {
        System.out.println(getUserRecursively(this).informationDisplay());
        parentMenu.show();
        parentMenu.execute();
    }
}
