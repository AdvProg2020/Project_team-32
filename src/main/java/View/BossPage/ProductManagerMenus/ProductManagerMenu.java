package View.BossPage.ProductManagerMenus;

import View.Menu;

public class ProductManagerMenu extends Menu {
    public ProductManagerMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage all products";
        subMenu.add(new RemoveProductCommand(this));
        this.addLoginOrLogout();
    }
}
