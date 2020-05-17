package Menus;

import java.util.ArrayList;

public class SortingMenu extends Menu {

    public SortingMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Sorting Menu";
        this.subMenu.add(new ShowAvailableSortsCommand(this));
        this.subMenu.add(new SortCommand(this));
        this.subMenu.add(new CurrentSortCommand(this));
        this.subMenu.add(new DisableSortCommand(this));
    }

}
