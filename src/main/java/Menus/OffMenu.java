package Menus;

import Model.Good;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class OffMenu extends Menu{
    private static ArrayList<Good> allOffs = new ArrayList<>(); // this arrayList added by ali sharifi

    public OffMenu(Menu parentMenu) {
        super(parentMenu);
        this.name = "Off menu";
        this.subMenu.add(new ShowProductCommand(this));
        this.subMenu.add(new OffMenuFilterCommand(this));
    }

    @Override
    public void show() {
        System.out.println(printAllOffs());
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    private static String printAllOffs(){
        String output ="";
        for (Seller allSeller : Seller.getAllSellers()) {
            for (Off off : allSeller.getOffs()) {
                for (Good good : off.getGoodsForOff()) {

                    //by ali sharifi
                    allOffs.add(good);
                    //------------------------

                    output+="**** goodName: "+good.getName()+" goodID: " +
                            good.getGoodID()+"purePrice:"+ good.getSellerAndPrices().get(allSeller.getUserName())+
                            " discounted: " + good.getSellerAndPrices().get(allSeller.getUserName())*(100-off.getDiscountPercent())/100+"\n";
                }
            }
        }return output;
    }
}
