package Menus;

import java.util.ArrayList;

public class InformationMenu extends Menu {
    public InformationMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "view personal info";
        subMenu.add(new InformationDisplayCommand(this));
        subMenu.add(new InformationEditorCommand(this));
    }

    @Override
    protected void show() {
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
