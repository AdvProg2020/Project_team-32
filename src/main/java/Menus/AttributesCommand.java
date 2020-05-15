package Menus;

import Controller.IndividualGoodController;

import java.util.ArrayList;

public class AttributesCommand extends Menu {

    public AttributesCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("general properties");
        for (String property : IndividualGoodController.getGood().getCategory().getGeneralProperties()) {
            System.out.println(property);
            System.out.println(IndividualGoodController.getGood().getProperty(property));
        }
        System.out.println("special properties");
        for (String property : IndividualGoodController.getGood().getCategory().getSpecialProperties()) {
            System.out.println(property);
            System.out.println(IndividualGoodController.getGood().getProperty(property));
        }
    }

    @Override
    protected void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
