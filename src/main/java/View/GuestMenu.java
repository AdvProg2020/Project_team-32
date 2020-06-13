package View;

import Model.Guest;

public class GuestMenu extends Menu {
    private Guest user;

    public GuestMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "guest menu";
        user = new Guest();
        subMenu.add(new GoodsMenu(this));
        subMenu.add(new OffMenu(this));
        this.addLoginOrLogout();
    }

    public Guest getUser() {
        return user;
    }
}
