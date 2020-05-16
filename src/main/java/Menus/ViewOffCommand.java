package Menus;

import Model.Good;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class ViewOffCommand extends  Menu{
    public ViewOffCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Offs (Seller)";
        subMenu.add(new ViewIndividualOffCommand(this));
        subMenu.add(new EditOffCommand(this));
        subMenu.add(new AddOffCommand(this));
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
