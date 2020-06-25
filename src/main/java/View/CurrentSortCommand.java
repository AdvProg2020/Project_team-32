package View;

import Controller.GoodController;

public class CurrentSortCommand extends Menu {

    public CurrentSortCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Current sort command";
    }

    @Override
    public void show() {
        /*
        System.out.println(SortCommand.getTypeOfSortWithInt(GoodController.getCurrentSort()));
        */
        System.out.println(GoodController.getCurrentSort());
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
