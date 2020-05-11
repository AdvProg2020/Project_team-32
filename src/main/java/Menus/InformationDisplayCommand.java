package Menus;

import java.util.ArrayList;

public class InformationDisplayCommand extends Menu {
    public InformationDisplayCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println(getUserRecursively(this).informationDisplay());
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
