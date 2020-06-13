package View;

import Controller.IndividualGoodController;
import Model.Good;

public class ShowCartProducts extends Menu {
    public ShowCartProducts(Menu parentMenu) {
        super(parentMenu);
        this.name="show product (Cart)";
        subMenu.add(new IndividualGoodMenu(this));
    }

    @Override
    public void show() {
        System.out.println("Enter good id:");
    }

    @Override
    public void execute() {
        String id = scanner.next();
        if(Good.getGoodById(id) == null){
            System.out.println("Good is not valid");
            parentMenu.show();
            parentMenu.execute();
        } else {
            //new IndividualGoodMenu();
            IndividualGoodController.setGoodId(id);
            individualGoodMenu.show();
            individualGoodMenu.execute();
        }
    }
}
