package Menus;

import Controller.Exeptions.GoodDoesNotExistException;
import Controller.GoodController;

import java.util.ArrayList;

public class RemoveProductCommand extends Menu {
    public RemoveProductCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter a product-ID:");
    }

    @Override
    protected void execute() {
        String Id = scanner.nextLine();
        try {
            GoodController.deleteGoodById(Id);
        } catch (GoodDoesNotExistException exception) {
            System.out.println("product does not exist");
        }
    }
}
