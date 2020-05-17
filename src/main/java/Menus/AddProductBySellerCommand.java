package Menus;

import Controller.Exeptions.InvalidPatternException;
import Controller.RequestController;
import Controller.SellerController;
import Model.Seller;

import java.util.ArrayList;

public class AddProductBySellerCommand extends Menu {

    public AddProductBySellerCommand(String name , ArrayList<Menu> subMenu){
        super(name, subMenu);
    }

    @Override
    public void show() {
        System.out.println("please enter GoodID ");
    }

    @Override
    public void execute() {
        System.out.println("please enter goodID,goodName,price,CompanyName,properties,explanations in this order:(without brackets)\n "+
                "[goodID] [name] [price] [companyName] [properties] [explanation]\n"+
                "note that properties must be in this order color:black,pattern:polkadot,... \n"+
                "note about spaces between obj");
        String input = scanner.nextLine();
        try{
            String request = SellerController.makeRequest(null,input.trim(),"^(\\S+) (\\S+) (\\d+) (\\S+) (\\S+:\\S+,)+ (.+)$");
            RequestController.addEditProductRequest(request.trim(),(Seller)getUserRecursively(this));
            System.out.println("requseet sent succesfully");
            parentMenu.show();
            parentMenu.execute();

        }catch(InvalidPatternException e){
            System.out.println("pattern is invalid \n"+
                    "note price must be in number \n"+
                    "[goodID] [name] [price] [companyName] [properties] [explanation]\n"+
                    "note that properties must be in this order color:black,pattern:polkadot,... \n"
                    "note about commas and spaces between obj");
            this.show();
            this.execute();
        }
    }
}
