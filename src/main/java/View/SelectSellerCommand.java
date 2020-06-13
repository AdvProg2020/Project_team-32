package View;

import Controller.Exeptions.UserDoesNotExistException;
import Controller.IndividualGoodController;

public class SelectSellerCommand extends Menu {

    public SelectSellerCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Select seller command";
    }

    @Override
    public void show() {
        System.out.println("Select a seller:");
    }

    @Override
    public void execute() {
        try {
            IndividualGoodController.selectSeller(scanner.nextLine());
        } catch (UserDoesNotExistException e) {
            System.out.println("sellerDoesn'tExistException");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
