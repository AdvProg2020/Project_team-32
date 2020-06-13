package View.BossPage;

import View.*;
import View.BossPage.CategoryManager.ManageCategoryMenu;
import View.BossPage.DiscountManagerMenus.CreateDiscountCommand;
import View.BossPage.DiscountManagerMenus.DiscountDisplayMenu;
import View.BossPage.ProductManagerMenus.ProductManagerMenu;
import View.BossPage.RequestManagerMenus.ManageRequestMenu;
import View.BossPage.UserMangerMenus.UserManagerMenu;
import View.InformationMenus.InformationMenu;
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
