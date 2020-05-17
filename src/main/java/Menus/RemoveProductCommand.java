package Menus;

import Controller.Exeptions.GoodDoesNotExistException;
import Controller.GoodController;

public class RemoveProductCommand extends Menu {

    public RemoveProductCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "remove";
    }

    @Override
    public void show() {
        System.out.println("enter a product-ID:");
    }

    @Override
    public void execute() {
        String Id = scanner.nextLine();
        try {
            GoodController.deleteGoodById(Id);
        } catch (GoodDoesNotExistException exception) {
            System.out.println("product does not exist");
        }
    }
}
