package Menus;

import java.util.ArrayList;

public class DigestMenu extends Menu {

    public DigestMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Digest Menu";
        this.subMenu.add(new AddToCartCommand(this));
        this.subMenu.add(new SelectSellerCommand(this));
    }

}

//subMenu of individualGoodsMenu
