package View;

import Controller.GoodController;
import Controller.Sort.SortType;

public class DisableSortCommand extends Menu {

    public DisableSortCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Disable sort command";
    }

    @Override
    public void show() {

    }

    @Override
    public void execute() {
        try {
            GoodController.getGoodController().sort(SortType.sortByNumberOfView);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
