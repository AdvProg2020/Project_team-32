package Menus;

import Controller.Exeptions.InvalidIDException;
import Controller.Exeptions.InvalidPatternException;
import Controller.RequestController;
import Controller.SellerController;
import Model.Off;
import Model.Seller;

import java.util.ArrayList;

public class EditOffCommand extends Menu {
    public EditOffCommand(Menu parentMenu) {
        super(parentMenu);
        this.name=" Edit Off";
    }

    @Override
    protected void show() {
        System.out.println("please enter offID ");
    }

    @Override
    protected void execute() {
        try{
            Off off= SellerController.checkOffID(((Seller)getUserRecursively(this)).getOffs(),scanner.nextLine());
            System.out.println("pleasse enter GoodIDs and initial date and end date and offPercent in this order:\n "+
                    "[GoodId1,goodid2,..] [year,month,day] [year,month,day] [offPercent]"+
                    "not about commas and spaces between obj");
            String input = scanner.nextLine();
            try{
                String request = SellerController.makeRequest(off.getOffID(),input.trim(),"^(\\S+,)+ (\\d+),(\\d+),(\\d+) (\\d+),(\\d+),(\\d+) (\\d+)$");
                RequestController.addEditOffRequest(request,(Seller)getUserRecursively(this));
                System.out.println("requseet sent succesfully");
                parentMenu.show();
                parentMenu.execute();

            }catch(InvalidPatternException e){
                System.out.println("pattern is invalid \n"+
                        "note that dates and offPercent must be numbers\n"+
                        "not about commas and spaces between obj");
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
