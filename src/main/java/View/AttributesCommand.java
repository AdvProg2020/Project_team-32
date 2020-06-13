package View;

import Controller.IndividualGoodController;

public class AttributesCommand extends Menu {

    public AttributesCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Attributes command";
    }

    @Override
    public void show() {
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
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
