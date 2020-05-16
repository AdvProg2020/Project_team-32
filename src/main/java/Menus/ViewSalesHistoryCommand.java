package Menus;

import Model.SellLog;
import Model.Seller;

import java.util.ArrayList;

public class ViewSalesHistoryCommand extends Menu {
    public ViewSalesHistoryCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Sales History";
    }

    @Override
        protected void show() {
        }

        @Override
        protected void execute() {
            for (SellLog allSellingLog : ((Seller) getUserRecursively(this)).getAllSellingLogs()) {
                System.out.println(allSellingLog.toString()+"\n");
            }
            parentMenu.show();
            parentMenu.execute();
        }
}
