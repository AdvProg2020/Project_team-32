package Controller;

import Menus.*;
import Menus.BossPage.BossMenu;
import Model.Guest;

import java.util.Scanner;

public class Controller {

    public static void main(String[] args) {
        Menu.bossMenu = new BossMenu(null);
        Menu.guestMenu = new GuestMenu(null);
        Menu.customerMenu = new CustomerMenu( null);
        Menu.sellerMenu = new SellerMenu(null);
        Menu.individualGoodMenu = new IndividualGoodMenu(null);
        Menu.guestMenu.show();
        Menu.guestMenu.execute();
    }
}
