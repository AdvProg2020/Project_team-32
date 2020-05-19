package Menus.InformationMenus;

import Menus.Menu;

public class InformationMenu extends Menu {

    public InformationMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "view personal info";
        subMenu.add(new InformationDisplayCommand(this));
        subMenu.add(new InformationEditorCommand(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
