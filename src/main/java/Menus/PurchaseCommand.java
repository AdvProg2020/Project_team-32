package Menus;

public class PurchaseCommand extends  Menu{
    public PurchaseCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Purchase";
        subMenu.add(new PayProcess(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
