package Menus;

import Controller.CustomerController;
import Model.Seller;

import java.util.ArrayList;

public class ViewCompanyInformaionCommand extends Menu{
    public ViewCompanyInformaionCommand(Menu parentMenu) {
        super(parentMenu);
        this.name ="View Company Information";
    }

    @Override
    protected void show() {
    }

    @Override
    protected void execute() {
        System.out.println(( (Seller)getUserRecursively(this)).getFactoryName());
        parentMenu.show();
        parentMenu.execute();
    }

}
