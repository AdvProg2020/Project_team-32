package View;

import Model.Good;
import Model.Seller;

public class ManageProductsMenu extends Menu {

    public ManageProductsMenu(Menu parentMenu) {
        super(parentMenu);
        this.name="Manage Product";
        subMenu.add(new ViewForProductCommand(this));
        subMenu.add(new ViewBuyersForProducts(this));
        subMenu.add(new AddProductCommand(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        int i=1;
        for (Good sellingGood : ((Seller) getUserRecursively(this)).getSellingGoods()) {
            System.out.println(i+": "+sellingGood.getName()+" / ID:" +sellingGood.getGoodID());
            i++;
        }
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
