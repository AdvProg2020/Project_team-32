package View;

import Controller.GoodController;

public class CurrentFiltersCommand extends Menu {

    public CurrentFiltersCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Current filters command";
    }

    @Override
    public void show() {
        GoodController.getGoodController().showCurrentFilters();
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
