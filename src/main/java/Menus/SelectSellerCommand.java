package Menus;

import Controller.Exeptions.UserDoesNotExistException;
import Controller.IndividualGoodController;

import java.util.ArrayList;

public class SelectSellerCommand extends Menu {

    public SelectSellerCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Select a seller:");
    }

    @Override
    protected void execute() {
        try {
            IndividualGoodController.selectSeller(scanner.nextLine());
        } catch (UserDoesNotExistException e) {
            System.out.println("sellerDoesn'tExistException");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
