package Menus;

import Controller.Filterable;
import Model.Guest;

public class LogoutCommand extends Menu {

    public LogoutCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Logout";
    }

    @Override
    public void show() {
    }

    @Override
    public void execute() {
        Filterable.reset();
        guestMenu.show();
        guestMenu.execute();
    }
}
