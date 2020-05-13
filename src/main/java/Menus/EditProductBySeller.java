package Menus;

import Controller.Exeptions.NumberOutOfBoundException;
import Controller.SellerController;
import Model.Seller;

import java.util.ArrayList;

public class EditProductBySeller extends Menu {
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
            ُُint n = Integer.parseInt(scanner.nextLine())
            System.out.println("please enter new price for yor good");
            int price = Integer.parseInt(scanner.nextLine());
            SellerController.editProducts(((Seller)getUserRecursively(this)),n,price);
            System.out.println("product editedd succesfully");
        }
        catch(NumberOutOfBoundException e){
            System.out.println("number is not acceptable , please enter another number");
            this.execute();
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
