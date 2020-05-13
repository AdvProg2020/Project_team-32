package Menus;

import Controller.SellerController;
import Model.Seller;

import java.util.ArrayList;

public class EditProductBySeller extends ManageProducts {
    public EditProductBySeller(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please enter the number you chosed");
    }

    @Override
    protected void execute() {
        try {
            System.out.println(SellerController.viewProduct(((Seller)getUserRecursively(this)),Integer.parseInt(scanner.nextLine())));
        }
        catch(Exception e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
