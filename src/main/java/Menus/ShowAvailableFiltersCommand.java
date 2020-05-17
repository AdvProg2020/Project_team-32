package Menus;

import Controller.GoodController;
import Model.Category;

import java.util.ArrayList;

public class ShowAvailableFiltersCommand extends Menu {

    public ShowAvailableFiltersCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("General Properties:");
        for (String property : Category.getGeneralProperties()) {
            System.out.println(property);
        }
        System.out.println("Special Properties:");
        for (String property : GoodController.getCurrentCategory().getSpecialProperties()) {
            System.out.println(property);
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
