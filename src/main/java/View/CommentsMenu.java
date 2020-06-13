package View;

public class CommentsMenu extends Menu {
    public CommentsMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Comments menu";
        this.subMenu.add(new ShowCommentsCommand(this));
        this.subMenu.add(new AddCommentCommand(this));
    }
}
