package Menus.BossPage;

import Menus.*;
import Menus.BossPage.CategoryManager.ManageCategoryMenu;
import Menus.BossPage.DiscountManagerMenus.CreateDiscountCommand;
import Menus.BossPage.DiscountManagerMenus.DiscountDisplayMenu;
import Menus.BossPage.ProductManagerMenus.ProductManagerMenu;
import Menus.BossPage.RequestManagerMenus.ManageRequestMenu;
import Menus.BossPage.UserMangerMenus.UserManagerMenu;
import Menus.InformationMenus.InformationMenu;
import Model.Boss;

public class BossMenu extends Menu {
  
    private Boss user;

    public BossMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "boss menu";
        subMenu.add(new InformationMenu(this));
        subMenu.add(new UserManagerMenu(this));
        subMenu.add(new ProductManagerMenu(this));
        subMenu.add(new CreateDiscountCommand(this));
        subMenu.add(new DiscountDisplayMenu(this));
        subMenu.add(new ManageRequestMenu(this));
        subMenu.add(new ManageCategoryMenu(this));
        this.addLoginOrLogout();
    }

    public void setUser(Boss user) {
        this.user = user;
    }

    public Boss getUser() {
        return user;
    }
}
