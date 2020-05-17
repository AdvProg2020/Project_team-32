package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class DisableFilterCommand extends Menu {

    public DisableFilterCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("Please enter a selected filter:");
    }

    @Override
    public void execute(){
        try{
            GoodController.disableFilter(scanner.nextLine());
        } catch (Exception e){
            System.out.println("Filter is not selected");
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
