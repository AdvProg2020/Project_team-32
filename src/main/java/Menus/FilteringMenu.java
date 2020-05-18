package Menus;

public class FilteringMenu extends Menu {

    public FilteringMenu(Menu parentMenu) {

        super(parentMenu);
        this.name = "filtering menu";
        this.subMenu.add(new ShowAvailableFiltersCommand(this));
        this.subMenu.add(new GoodMenuFilterCommand(this));
        this.subMenu.add(new CurrentFiltersCommand(this));
        this.subMenu.add(new DisableFilterCommand(this));
    }

}
