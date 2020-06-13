package View;

import Controller.IndividualGoodController;

public class AddToCartCommand extends Menu {

    public AddToCartCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Add to cart command";
    }

    @Override
    public void show() {

    }

    @Override
    public void execute() {
        try {
            IndividualGoodController.addToCart(Menu.getUserRecursively(this));
        } catch (Exception e) {
            System.out.println("Seller is not selected");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
