package Menus;

import java.util.ArrayList;

public class InformationDisplayCommand extends Menu {
    public InformationDisplayCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "view information";
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
