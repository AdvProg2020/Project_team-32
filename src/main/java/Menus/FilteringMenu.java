package Menus;

import java.util.ArrayList;

public class FilteringMenu extends Menu {

    public FilteringMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "filtering menu";
        this.subMenu.add(new ShowAvailableFiltersCommand(this));
        this.subMenu.add(new FilterCommand(this));
        this.subMenu.add(new CurrentFiltersCommand(this));
        this.subMenu.add(new DisableFilterCommand(this));
    }

}
