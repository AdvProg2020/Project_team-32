package Menus;

import Model.Good;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class ViewOffCommand extends  Menu{
    public ViewOffCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        int i=1;
        for (Off off : ((Seller) getUserRecursively(this)).getOffs()) {
            System.out.println(i+": "+off.getOffID());
            i++;
        }
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
