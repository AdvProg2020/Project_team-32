package Menus.BossPage.RequestManagerMenus;

import Controller.RequestController;
import Menus.Menu;
import Model.Request;

public class ShowRequestDetailCommand extends Menu {


    public ShowRequestDetailCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "details";
    }

    @Override
    public void show() {
        System.out.println("enter request number:");
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(scanner.nextLine());
        Request request = RequestController.getAllRequest().get(index);
        System.out.println(request.getRequestType() + "from " + request.getSeller().getUserName() + "\n" + request.getRequest());
        parentMenu.show();
        parentMenu.execute();
    }
}
