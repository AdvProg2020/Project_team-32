package Menus;

import Menus.InformationMenus.InformationMenu;
import Model.Customer;

import java.util.ArrayList;

public class CustomerMenu extends Menu {

    private Customer user;

    public CustomerMenu(Menu parentMenu) {
        super(parentMenu);
        this.name="Customer Menu";
        subMenu.add(new InformationMenu(this));
        subMenu.add(new ViewCard(this));
        subMenu.add(new PurchaseCommand(this));
        subMenu.add(new PayProcess(this));
        subMenu.add(new viewOrdersMenu(this));
        subMenu.add(new ViewBalanceCommand(this));
        subMenu.add(new ViewDiscountCodesCommand(this));
    }

    public void setUser(Customer user) {
        this.user = user;
    }

    public Customer getUser() {
        return user;
    }

}
