package Menus;

import Controller.BossController;
import Controller.Exeptions.DiscountDoesNotExistException;
import Model.Discount;

public class DiscountEditCommand extends Menu {

    public DiscountEditCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "edit discount code";
    }

    @Override
    public void show() {
        System.out.println("enter a discountId to edit its information:");
    }

    @Override
    public void execute() {
        String discountId = scanner.nextLine();
        try {
            Discount discount = BossController.getDiscountById(discountId);
            System.out.println("please use this format to change discount information:\n[discountId] [year,month,day] [discountPercent] [max amount]");
            String command = scanner.nextLine();
             if (commandValidation(command)) {
                 BossController.editDiscount(command.split(" "), discount);
                 System.out.println("information changed successfully");
             }
             else {
                 System.out.println("invalid format.");
             }
        } catch (DiscountDoesNotExistException e) {
            System.out.println("discountId does not exist.");
        }
        parentMenu.show();
        parentMenu.execute();
    }

    private boolean commandValidation(String command) {
        return command.matches("\\S+ \\d+,\\d+,\\d+ \\d+ \\d+");
    }

}
