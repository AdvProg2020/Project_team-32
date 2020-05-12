package Menus;

import Controller.BossController;

import java.util.ArrayList;

public class CreateDiscountCommand extends Menu {

    public CreateDiscountCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("please use this format to create a discount:\n[discountId] [year,month,day] [discountPercent] [max amount]");
    }

    @Override
    protected void execute() {
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
