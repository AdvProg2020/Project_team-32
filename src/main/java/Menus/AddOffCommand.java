package Menus;

import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Controller.RequestController;
import Controller.SellerController;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class AddOffCommand extends Menu {
    public AddOffCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="Add off ";
    }

    @Override
    protected void show() {
        System.out.println("you are in add off menu ");
    }

    @Override
    protected void execute() {
            System.out.println("pleasse enter offID and GoodIDs and initial date and end date and offPercent in this order: \n "+
                    "[offID] [GoodId1,goodid2,..] [year,month,day] [year,month,day] [offPercent]"+
                    "note about commas and spaces between obj");
            String input = scanner.nextLine();
            try{
                String request = SellerController.makeRequest(null,input.trim(),"^(\\S+) (\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)$");
                RequestController.addOffRequest(request.trim(),(Seller)getUserRecursively(this));
                System.out.println("requseet sent succesfully");
                parentMenu.show();
                parentMenu.execute();

            }catch(InvalidPatternException e) {
                System.out.println("pattern is invalid \n" +
                        "note that dates and offPercent must be numbers\n" +
                        "not about commas and spaces between obj");
                this.show();
                this.execute();
            }

    }
}
