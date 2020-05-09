package Menus;

import java.util.ArrayList;

public class ViewBalanceCommand extends Menu{

    public ViewBalanceCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }

    @Override
    protected void show() {
        System.out.println(getUserRecursively(this).getCredit());
    }
}
