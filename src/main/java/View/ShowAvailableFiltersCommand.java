package View;

import Controller.GoodController;
import Model.Category;

public class ShowAvailableFiltersCommand extends Menu {

    public ShowAvailableFiltersCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Show available filter command";
    }

    @Override
    public void show() {
        System.out.println("General Properties:");
        for (String property : Category.getGeneralProperties()) {
            System.out.println(property);
        }
        System.out.println("Special Properties:");
        for (String property : GoodController.getGoodController().getCurrentCategory().getSpecialProperties()) {
            System.out.println(property);
        }

        System.out.println("Category");
        for (Category category : GoodController.getGoodController().getCurrentCategory().getSubCategory()) {
            System.out.println(category.getName());
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
