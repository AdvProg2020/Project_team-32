package View.LoginLogoutCommands;

import Controller.Filterable;
import View.Menu;

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
