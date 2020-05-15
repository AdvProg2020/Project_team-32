package Menus;

import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Controller.Exeptions.NumberOutOfBoundException;
import Controller.RequestController;
import Controller.SellerController;
import Model.Good;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class EditProductBySeller extends Menu {
    public EditProductBySeller(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please enter GoodID ");
    }

    @Override
    protected void execute() {
        try{
            Good good = SellerController.checkGoodID(((Seller)getUserRecursively(this)).getSellingGoods(),scanner.nextLine());
            System.out.println("please enter goodName,price,CompanyName,properties,explanations in this order:(without brackets)\n "+
                    "[name] [price] [companyName] [properties] [explanation]\n"+
                    "note that properties must be in this order color:black,pattern:polkadot,... \n"+
                    "note about spaces between obj");
            String input = scanner.nextLine();
            try{
                String request = SellerController.makeRequest(good.getGoodID(),input.trim(),"^(\\S+) (\\d+) (\\S+) (\\S+:\\S+,)+ (.+)$");
                RequestController.addEditProductRequest(request,(Seller)getUserRecursively(this));
                System.out.println("requseet sent succesfully");
                parentMenu.show();
                parentMenu.execute();

            }catch(InvalidPatternException e){
                System.out.println("pattern is invalid \n"+
                        "note price must be in number \n"+
                        "[name] [price] [companyName] [properties] [explanation]\n"+
                        "note that properties must be in this order color:black,pattern:polkadot,... \n"
                        "note about commas and spaces between obj");
                this.show();
                this.execute();
            }
        }
        catch (InvalidIDException e){
            System.out.println("Invalid ID");
            this.show();
            this.execute();
        }
    }
}
