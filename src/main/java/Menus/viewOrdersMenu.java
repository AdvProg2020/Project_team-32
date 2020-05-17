package Menus;

import Model.BuyLog;
import Model.Customer;

public class viewOrdersMenu extends Menu {

    public viewOrdersMenu(Menu parentMenu) {
        super(parentMenu);
        this.name="View Orders";
        subMenu.add(new RateCommand(this));
        subMenu.add(new ShowOrderCommand(this));
    }

    @Override
    public void show() {
        for (BuyLog log : ((Customer)getUserRecursively(this)).getAllBuyLogs()) {
            System.out.println("logID : "+log.getLogID());
        }
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

}
