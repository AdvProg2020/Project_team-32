package Menus;

import java.util.ArrayList;

public class ViewBalanceCommand extends Menu{


    public ViewBalanceCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
