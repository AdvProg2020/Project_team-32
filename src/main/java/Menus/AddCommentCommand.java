package Menus;

import java.util.ArrayList;

public class AddCommentCommand extends Menu {

    public AddCommentCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("Title:\nComment:");
    }

    @Override
    public void execute() {

    }
}
//submenu of command menu