package Menus;

import java.util.ArrayList;
import java.util.Scanner;

abstract public class Menu {
    protected ArrayList<Menu> subMenu;
    protected Menu parentMenu;
    protected String name;
    protected Scanner scanner = new Scanner(System.in);

    public Menu(String name,ArrayList<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    protected static void show(){
    }

    protected static void execute(){
    }

}
