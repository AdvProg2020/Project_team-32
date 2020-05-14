package Menus;

import Model.BuyLog;
import Model.Customer;

import java.util.ArrayList;

public class viewOrdersMenu extends Menu {


    public viewOrdersMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (BuyLog log : ((Customer)getUserRecursively(this)).getAllBuyLogs()) {
            System.out.println(log);
        }
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }

    //showOrderCommand and rateCommand are submenus of this menu

    //implemented by ali sharifi
}
