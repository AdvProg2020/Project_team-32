package Menus;

import java.util.ArrayList;

public class CustomerLogoutCommand extends Menu{


    public CustomerLogoutCommand(Menu parentMenu) {
        super(parentMenu);
    }

    @Override
    public void show() {

    }

    @Override
    public void execute() {
        guestMenu.show();
        guestMenu.execute();
    }
}
