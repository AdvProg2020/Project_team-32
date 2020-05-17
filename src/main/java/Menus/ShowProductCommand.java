package Menus;

import Controller.IndividualGoodController;
import Model.Good;

public class ShowProductCommand extends Menu {
    public ShowProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="show individual product";
    }

    @Override
    public void show() {
        System.out.println("Enter good id:");
    }

    @Override
    public void execute() {
        String id = scanner.next();
        if(Good.getGoodById(id) == null){
            System.out.println("Good is not valid");
        } else {
            IndividualGoodController.setGoodId(id);
            individualGoodMenu.show();
            individualGoodMenu.execute();
        }
    }
}

//submenu of goodMenu