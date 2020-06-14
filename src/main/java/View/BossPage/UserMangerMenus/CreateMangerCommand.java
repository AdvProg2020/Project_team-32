package View.BossPage.UserMangerMenus;

import Controller.BossController;
import Controller.Exeptions.DuplicateUsernameException;
import View.Menu;

public class CreateMangerCommand extends Menu {

    public CreateMangerCommand(Menu parentMenu) {
        super(parentMenu);
        this.name="create manager profile";
    }

    @Override
    public void show() {
        System.out.println("Please use this format to create a new manager:\ncreate manager [username] [password]");
    }

    @Override
    public void execute() {
       /* String command = scanner.nextLine();
        try {
            BossController.createManager(command.split(" "));
            System.out.println("manger created successfully");
        } catch (DuplicateUsernameException exception) {
            System.out.println("this username is already exist");
        }
        parentMenu.show();
        parentMenu.execute();*/
    }
}
