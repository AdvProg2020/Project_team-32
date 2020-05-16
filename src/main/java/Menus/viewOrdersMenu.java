package Menus;

import Model.BuyLog;
import Model.Customer;

import java.util.ArrayList;

public class viewOrdersMenu extends Menu {

    public viewOrdersMenu(Menu parentMenu) {
        super(parentMenu);
        this.name="View Orders";
        subMenu.add(new RateCommand(this));
        subMenu.add(new ShowOrderCommand(this));
    }

    @Override
    protected void show() {
        for (BuyLog log : ((Customer)getUserRecursively(this)).getAllBuyLogs()) {
            System.out.println("logID : "+log.getLogID());
        }
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }

    //showOrderCommand and rateCommand are submenus of this menu

    //implemented by ali sharifi
}
