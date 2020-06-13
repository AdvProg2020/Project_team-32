package View.BossPage.UserMangerMenus;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;
import View.Menu;

public class DeleteUserByBossCommand extends Menu {

    public DeleteUserByBossCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "delete user";
    }

    @Override
    public void show() {
        System.out.println("Please enter a username:");
    }

    @Override
    public void execute() {
        String username = scanner.nextLine();
        try {
            AccountController.deleteUser(username);
            System.out.println("user is deleted successfully");
        } catch (UserDoesNotExistException exception) {
            System.out.println("username is not correct");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
