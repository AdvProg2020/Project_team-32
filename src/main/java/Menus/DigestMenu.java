package Menus;

import Controller.IndividualGoodController;

import java.util.ArrayList;

public class DigestMenu extends Menu {

    public DigestMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Digest Menu";
        this.subMenu.add(new AddToCartCommand(this));
        this.subMenu.add(new SelectSellerCommand(this));
    }

    @Override
    public void show() {
        System.out.println("Summary of product");
        System.out.println(IndividualGoodController.getGood().getSummary(IndividualGoodController.getSeller()));
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}

//subMenu of individualGoodsMenu
