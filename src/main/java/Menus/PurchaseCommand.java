package Menus;

import Controller.Exeptions.NotLogedInException;
import Controller.PurchaseController;

import java.util.ArrayList;

public class PurchaseCommand extends  Menu{

    public PurchaseCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
