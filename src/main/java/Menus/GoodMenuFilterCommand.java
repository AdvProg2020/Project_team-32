package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class GoodMenuFilterCommand extends FilterCommand {

    public GoodMenuFilterCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Good menu filter";
    }

    @Override
    public void execute() {
        String filter = scanner.nextLine();
        String value = scanner.nextLine();
        try {
            GoodController.filter(filter, value);
        } catch (Exception e) {
            System.out.println("CannotFilter");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
