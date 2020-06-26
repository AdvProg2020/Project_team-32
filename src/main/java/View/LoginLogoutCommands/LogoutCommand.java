package View.LoginLogoutCommands;

import Controller.Filterable;
import Controller.GoodController;
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
        GoodController.getGoodController().reset();
        guestMenu.show();
        guestMenu.execute();
    }
}
