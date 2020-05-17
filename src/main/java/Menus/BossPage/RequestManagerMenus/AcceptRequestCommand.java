package Menus.BossPage.RequestManagerMenus;

import Controller.RequestController;
import Menus.Menu;
import Model.Request;

public class AcceptRequestCommand extends Menu {


    public AcceptRequestCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "accept";
    }

    @Override
    public void show() {
        System.out.println("enter request number:");
    }

    @Override
    public void execute() {
        int index = Integer.parseInt(scanner.nextLine());
        Request request = RequestController.getAllRequest().get(index);
        try {
            RequestController.acceptRequest(request);
        } catch (Exception e) {
            System.out.println("can not accept request.");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
