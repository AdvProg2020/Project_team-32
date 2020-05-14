package Menus;

import Controller.RequestController;
import Model.Request;

import java.util.ArrayList;

public class AcceptRequestCommand extends Menu {

    public AcceptRequestCommand(String name, ArrayList<Menu> subMenu) {
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
        try {
            RequestController.acceptRequest(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
