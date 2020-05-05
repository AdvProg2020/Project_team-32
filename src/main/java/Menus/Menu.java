package Menus;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Menu {
    //this methods should be static
    protected static BossMenu bossMenu;
    protected static GuestMenu guestMenu;
    protected static CustomerMenu customerMenu;
    protected static SellerMenu sellerMenu;
    private static GoodsMenu goodsMenu;
    private static OffMenu offMenu;
    private static LoginMenu loginMenu;
    private static RegisterMenu registerMenu;


    protected ArrayList<Menu> subMenu;
    protected Menu parentMenu;
    protected String name;
    protected static Scanner scanner = new Scanner(System.in);


    public Menu(String name,ArrayList<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    protected void show(){
        for(int i=0 ; i < subMenu.size() ; i++) {
            System.out.println("" + i + "." + subMenu.get(i));
        }
        System.out.println(""+ subMenu.size() + ".Return");
        System.out.println("Please select a number:");
    }

    protected void execute(){
        int command = Integer.parseInt(scanner.nextLine());
        if(command == subMenu.size()){
            parentMenu.show();
            parentMenu.execute();
        }
        else{
            subMenu.get(command).show();
            subMenu.get(command).execute();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
