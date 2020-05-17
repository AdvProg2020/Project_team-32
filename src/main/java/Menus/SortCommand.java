package Menus;

import Controller.GoodController;

import java.util.ArrayList;

public class SortCommand extends Menu {

    public SortCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Sort command";
    }

    @Override
    public void show() {
        System.out.println("1. sort by time \n2. sort by point \n3.sort by number of views");
    }

    @Override
    public void execute() {
        try {
            GoodController.sort(Integer.parseInt(scanner.nextLine()));
        } catch (Exception e) {
            System.out.println("invalidSortMethod");
        }
        parentMenu.show();
        parentMenu.execute();
    }

    public static String getTypeOfSortWithInt(int i){
        if( i == 1 ){
            return "Sort By Time.";
        } else if (i == 2){
            return "Sort By Point.";
        } else {
            return "Sort By Number of View.";
        }
    }
}
//subMenu of SortingMenu
