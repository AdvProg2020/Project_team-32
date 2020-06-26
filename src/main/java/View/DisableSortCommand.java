package View;

import Controller.GoodController;

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
        //    GoodController.sort(3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
