package Menus;

import java.util.ArrayList;

abstract public class Menu {
    protected ArrayList<Menu> subMenu;
    protected Menu parentMenu;
    protected String name;

    public Menu(String name,ArrayList<Menu> subMenu) {
        this.subMenu = subMenu;
    }

    protected static void show(){
    }

    protected static void execute(){
    }

}
