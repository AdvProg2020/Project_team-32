package View;

import Controller.PurchaseController;
import Model.Off;
import Model.Seller;

public class ViewOffCommand extends  Menu{
    public ViewOffCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="View Offs (Seller)";
        subMenu.add(new ViewIndividualOffCommand(this));
        subMenu.add(new EditOffCommand(this));
        subMenu.add(new AddOffCommand(this));
        this.addLoginOrLogout();
    }

    @Override
    public void show() {
        int i=1;
        PurchaseController.passTime();
        for (Off off : ((Seller) getUserRecursively(this)).getOffs()) {
            System.out.println(i+": "+off.getOffID());
            i++;
        }
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
