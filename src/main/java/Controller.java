import Menus.*;
import Model.Customer;

public class Controller {
    private Menu currentMenu;
    public void login(){
        if(currentMenu instanceof LoginMenu){
            ((LoginMenu) currentMenu).login();
        }
    }
}
