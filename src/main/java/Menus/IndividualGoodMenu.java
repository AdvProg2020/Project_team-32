package Menus;

import java.util.ArrayList;

public class IndividualGoodMenu extends Menu {

    private String goodId;

    public IndividualGoodMenu(String name, ArrayList<Menu> subMenu, String goodId) {
        super(name, subMenu);
        this.goodId = goodId;
    }

}
