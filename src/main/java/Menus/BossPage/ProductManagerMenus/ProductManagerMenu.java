package Menus.BossPage.ProductManagerMenus;

import Menus.Menu;

public class ProductManagerMenu extends Menu {
    public ProductManagerMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage all products";
        subMenu.add(new RemoveProductCommand(this));
    }
}
