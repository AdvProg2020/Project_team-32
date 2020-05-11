package Menus;

import Model.BuyLog;

import java.util.ArrayList;

public class viewOrdersCommand extends Menu {
    public viewOrdersCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        for (BuyLog log : getUserRecursively(this).getBuyLogs()) {
            System.out.println(log);
        }
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
