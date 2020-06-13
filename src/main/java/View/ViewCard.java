package View;

public class ViewCard extends Menu {
    public ViewCard(Menu parentMenu) {
        super(parentMenu);
        this.name="View Card";
        subMenu.add(new showProductsCard(this));
        subMenu.add(new ShowProductCommand(this));
        subMenu.add(new IncreaseCommand(this));
        subMenu.add(new DecreaseCommand(this));
        subMenu.add(new ShowTotalPricesCommand(this));
        subMenu.add(new PurchaseCommand(this));
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
