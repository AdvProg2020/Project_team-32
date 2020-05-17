package Menus;

import Controller.BossController;

public class CreateDiscountCommand extends Menu {


    public CreateDiscountCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "create discount code";
    }

    @Override
    public void show() {
        System.out.println("please use this format to create a discount:\n[discountId] [year,month,day] [discountPercent] [max amount]");
    }

    @Override
    public void execute() {
        String command = scanner.nextLine();
        if (commandValidation(command)) {
            BossController.createDiscount(command.split(" "));
        }
        else{
            System.out.println("invalid command");
        }
        parentMenu.show();
        parentMenu.execute();
    }

    private boolean commandValidation(String command) {
        return command.matches("\\S+ \\d+,\\d+,\\d+ \\d+ \\d+");
    }
}
