package View;

import Model.SellLog;
import Model.Seller;

public class ViewSalesHistoryCommand extends Menu {
    public ViewSalesHistoryCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Sales History";
    }

    @Override
    public void show() {
        }

        @Override
        public void execute() {
            for (SellLog allSellingLog : ((Seller) getUserRecursively(this)).getAllSellingLogs()) {
                System.out.println(allSellingLog.toString()+"\n");
            }
            parentMenu.show();
            parentMenu.execute();
        }
}
