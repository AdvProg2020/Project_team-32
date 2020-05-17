package Menus;

import Controller.IndividualGoodController;
import Model.Good;

import java.util.ArrayList;
public class CompareCommand extends Menu {

    public CompareCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }


    @Override
    public void show() {
        System.out.println("Please enter another goodId:");
    }

    @Override
    public void execute() {
        String id = scanner.nextLine();
        Good otherGood = Good.getGoodById(id);
        System.out.println("general properties");
        for (String property : IndividualGoodController.getGood().getCategory().getGeneralProperties()) {
            System.out.println(property);
            System.out.println("selected Good");
            System.out.println(IndividualGoodController.getGood().getProperty(property));
            System.out.println("other good");
            System.out.println(otherGood.getProperty(property));
        }
        System.out.println("special properties");
        for (String property : IndividualGoodController.getGood().getCategory().getSpecialProperties()) {
            System.out.println(property);
            System.out.println("selected Good");
            System.out.println(IndividualGoodController.getGood().getProperty(property));
            System.out.println("other good");
            System.out.println(otherGood.getProperty(property));
        }

        parentMenu.show();
        parentMenu.execute();
    }
}
