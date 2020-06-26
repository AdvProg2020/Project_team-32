package View.BossPage.ProductManagerMenus;

import Controller.Exeptions.GoodDoesNotExistException;
import Controller.GoodController;
import View.Menu;

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
            GoodController.getGoodController().deleteGoodById(Id);
        } catch (GoodDoesNotExistException exception) {
            System.out.println("product does not exist");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
