package View;

public class IndividualGoodMenu extends Menu {

    public IndividualGoodMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Individual good menu";
        this.subMenu.add(new DigestMenu(this));
        this.subMenu.add(new AttributesCommand(this));
        this.subMenu.add(new CommentsMenu(this));
    }

}
