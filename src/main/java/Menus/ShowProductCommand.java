package Menus;

import Model.Good;

import java.util.ArrayList;

public class ShowProductCommand extends Menu {
    public ShowProductCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Enter good id:");
    }

    @Override
    protected void execute() {
        if(Good.getGoodById(scanner.next()) == null){
            System.out.println("Good is not valid");
        } else {
            individualGoodMenu.show();
            individualGoodMenu.execute();
        }
    }
}

//submenu of goodMenu