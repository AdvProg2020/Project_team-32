package Menus;

import Controller.IndividualGoodController;
import Model.Comment;

import java.util.ArrayList;

public class ShowCommentsCommand extends Menu {

    public ShowCommentsCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    public void show() {
        for (Comment comment : IndividualGoodController.getGood().getAllComments()) {
            System.out.println(comment);
        }
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
//submenu of comment menu