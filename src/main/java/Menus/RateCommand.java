package Menus;

import Controller.CustomerController;
import Model.Customer;

import java.util.ArrayList;

public class RateCommand extends Menu {


    public RateCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Please enter a product ID and a rate between 1 and 5 respectively");
    }

    @Override
    protected void execute() {
        try {
            CustomerController.rateProduct(scanner.nextLine(), scanner.nextInt(), (Customer) getUserRecursively(this));
        } catch (Exception e){
            System.out.println("Can not rate product");

            //exception mitavanad ham be dalile kharide nashodan kala bashad va ham be khatere pointe na motabar
        }
    }
}
