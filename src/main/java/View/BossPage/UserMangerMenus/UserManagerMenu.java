package View.BossPage.UserMangerMenus;

import View.Menu;

public class UserManagerMenu extends Menu {
    public UserManagerMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "manage users";
        subMenu.add(new ViewUserCommand(this));
        subMenu.add(new DeleteUserByBossCommand(this));
        subMenu.add(new CreateMangerCommand(this));
        this.addLoginOrLogout();
    }
}
