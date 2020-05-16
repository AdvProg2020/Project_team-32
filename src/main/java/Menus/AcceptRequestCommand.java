package Menus;

import Controller.RequestController;
import Model.Request;

import java.util.ArrayList;

public class AcceptRequestCommand extends Menu {


    public AcceptRequestCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "accept";
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
