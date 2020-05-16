package Menus;

import java.util.ArrayList;

public class ViewBalanceCommand extends Menu{


    public ViewBalanceCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Balance";
    }

    @java.lang.Override
    protected void show() {
        System.out.println(Menu.getUserRecursively(this).getCredit());
    }

    @java.lang.Override
    protected void execute() {
        //super.execute();
        parentMenu.show();
        parentMenu.execute();
    }
}
