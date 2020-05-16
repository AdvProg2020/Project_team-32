package Menus;

public class PurchaseCommand extends  Menu{
    public PurchaseCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Purchase";
        subMenu.add(new PayProcess(this));
    }

    @Override
    protected void show() {
        super.show();
    }

    @Override
    protected void execute() {
        super.execute();
    }
}
