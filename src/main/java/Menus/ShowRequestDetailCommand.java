package Menus;

import Controller.RequestController;
import Model.Request;

import java.util.ArrayList;

public class ShowRequestDetailCommand extends Menu {

    public ShowRequestDetailCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("enter request number:");
    }

    @Override
    protected void execute() {
        int index = Integer.parseInt(scanner.nextLine());
        Request request = RequestController.getAllRequest().get(index);
        System.out.println(request.getRequestType() + "from " + request.getSeller().getUserName() + "\n" + request.getRequest());
        parentMenu.show();
        parentMenu.execute();
    }
}
