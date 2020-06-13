package View;

import Controller.IndividualGoodController;
import Model.Comment;

public class ShowCommentsCommand extends Menu {

    public ShowCommentsCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Show Comments command";
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