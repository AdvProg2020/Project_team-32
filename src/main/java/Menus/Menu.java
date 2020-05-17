package Menus;

import Menus.BossPage.BossMenu;
import Model.Person;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Menu {
    //this methods should be static
    public static BossMenu bossMenu;
    public static GuestMenu guestMenu;
    public static CustomerMenu customerMenu;
    public static SellerMenu sellerMenu;
    private static GoodsMenu goodsMenu;
    private static OffMenu offMenu;
    private static LoginCommand loginCommand;
    private static RegisterCommand registerCommand;
    public static IndividualGoodMenu individualGoodMenu;

    protected ArrayList<Menu> subMenu;
    protected Menu parentMenu;
    protected String name;
    protected static Scanner scanner = new Scanner(System.in);


    public Menu(Menu parentMenu) {
        this.parentMenu = parentMenu;
        this.subMenu = new ArrayList<>();
    }

    public void show(){
        for(int i=0 ; i < subMenu.size() ; i++) {
            System.out.println("" + i + "." + subMenu.get(i));
        }
        if(parentMenu == null){
            System.out.println(""+ subMenu.size() + ".Exit");
        }
        else {
            System.out.println(""+ subMenu.size() + ".Return");
        }
        System.out.println("Please select a number:");
    }

    public void execute(){
        int command = Integer.parseInt(scanner.nextLine());
        if(command == subMenu.size()){
            if(parentMenu==null)
                return;
            parentMenu.show();
            parentMenu.execute();

        }
        else{
            subMenu.get(command).show();
            subMenu.get(command).execute();
        }
    }

    public static Person getUserRecursively(Menu menu){
        if(menu instanceof BossMenu)
            return ((BossMenu) menu).getUser();
        if(menu instanceof CustomerMenu)
            return ((CustomerMenu) menu).getUser();
        if(menu instanceof SellerMenu)
            return ((SellerMenu) menu).getUser();
        if(menu instanceof GuestMenu)
            return ((GuestMenu)menu).getUser();
        return getUserRecursively(menu.parentMenu);
    }


    @Override
    public String toString() {
        return name;
    }

}
