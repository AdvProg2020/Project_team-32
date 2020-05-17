package Menus;

import java.util.ArrayList;

public class CustomerLogoutCommand extends Menu{


    public CustomerLogoutCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
