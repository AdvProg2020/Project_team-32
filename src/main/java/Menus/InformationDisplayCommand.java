package Menus;

public class InformationDisplayCommand extends Menu {
    public InformationDisplayCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "view information";
    }

    @Override
    public void show() {
        System.out.println(getUserRecursively(this).informationDisplay());
    }

    @Override
    public void execute() {
        parentMenu.show();
        parentMenu.execute();
    }
}
