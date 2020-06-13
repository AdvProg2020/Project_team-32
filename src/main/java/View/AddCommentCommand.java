package View;

public class AddCommentCommand extends Menu {

    public AddCommentCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "Add comment Command";
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