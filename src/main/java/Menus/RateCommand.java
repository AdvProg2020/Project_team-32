package Menus;

import Controller.CustomerController;
import Model.Customer;

public class RateCommand extends Menu {

    public RateCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Rate Command";
    }

    @Override
    public void show() {
        System.out.println("Please enter a product ID and a rate between 1 and 5 respectively");
    }

    @Override
    public void execute() {
        try {
            CustomerController.rateProduct(scanner.nextLine().trim(), scanner.nextInt(), (Customer) getUserRecursively(this));
        } catch (Exception e){
            System.out.println("Can not rate product");
            this.show();
            this.execute();
            //exception mitavanad ham be dalile kharide nashodan kala bashad va ham be khatere pointe na motabar
        }
    }
}
