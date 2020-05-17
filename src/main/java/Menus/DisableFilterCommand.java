package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class DisableFilterCommand extends Menu {

    public DisableFilterCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Disable filter command";
    }

    @Override
    public void show() {
        System.out.println("Please enter a selected filter:");
    }

    @Override
    public void execute(){
        try {
            GoodController.disableFilter(scanner.nextLine());
        } catch (Exception e){
            System.out.println("Filter is not selected");
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
