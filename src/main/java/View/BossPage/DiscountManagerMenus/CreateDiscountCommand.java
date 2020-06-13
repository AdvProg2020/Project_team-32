package View.BossPage.DiscountManagerMenus;

import Controller.BossController;
import Controller.Exeptions.UserDoesNotExistException;
import View.Menu;
import Model.Person;

import java.util.ArrayList;

public class CreateDiscountCommand extends Menu {


    public CreateDiscountCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "create discount code";
    }

    @Override
    public void show() {
        System.out.println("please use this format to create a discount:\n[discountId] [year,month,day] [discountPercent] [max amount] [use number]");
    }

    @Override
    public void execute() {
        String command = scanner.nextLine();
        if (commandValidation(command)) {
            ArrayList<Person> users= new ArrayList<>();
            System.out.println("enter username you want to give them discount:");
            while (true){
                String username = scanner.nextLine();
                if(username.equals("%0%")){
                    break;
                }
                try {
                    users.add(Person.getPersonByUserName(username));
                } catch (UserDoesNotExistException e) {
                    System.out.println("username does not exist.");
                }
                System.out.println("enter another username or %0% to end process");
            }
            BossController.createDiscount(command.split(" "),users);
            System.out.println("discount created successfully.");
        }
        else{
            System.out.println("invalid command");
        }
        parentMenu.show();
        parentMenu.execute();
    }

    private boolean commandValidation(String command) {
        return command.matches("\\S+ \\d+,\\d+,\\d+ \\d+ \\d+ \\d+");
    }
}
