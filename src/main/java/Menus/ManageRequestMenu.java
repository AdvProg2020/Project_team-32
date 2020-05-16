package Menus;

import Controller.RequestController;
import Model.Request;

import java.util.ArrayList;

public class ManageRequestMenu extends Menu {

    public ManageRequestMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage requests";
        subMenu.add(new ShowRequestDetailCommand(this));
        subMenu.add(new AcceptRequestCommand(this));
        subMenu.add(new DeclineRequestCommand(this));
    }

    @Override
    protected void show() {
        ArrayList<Request> allRequest = RequestController.getAllRequest();
        for(int i=0 ; i<allRequest.size(); i++){
            System.out.println( "" + i + "-" + allRequest.get(i).getRequestType() + "from " + allRequest.get(i).getSeller().getUserName());
        }
        super.show();
    }

}
