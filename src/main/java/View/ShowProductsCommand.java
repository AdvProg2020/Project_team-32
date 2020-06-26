package View;

import Controller.GoodController;
import Model.Good;

public class ShowProductsCommand extends Menu {

    public ShowProductsCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Show products command";
    }

    @Override
    public void show() {
        for (Good good : GoodController.getGoodController().getSelectedGoods()) {
            System.out.println(good);
        }
    }
}

// this command shows all goods satisfying filters and category
