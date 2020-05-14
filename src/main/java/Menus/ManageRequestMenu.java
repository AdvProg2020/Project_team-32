package Menus;

import Controller.RequestController;
import Model.Request;

import java.util.ArrayList;

public class ManageRequestMenu extends Menu {
    public ManageRequestMenu(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
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
