package View.BossPage.UserMangerMenus;

import Controller.AccountController;
import Controller.Exeptions.UserDoesNotExistException;
import View.Menu;
import Model.Person;

public class ViewUserCommand extends Menu {

    public ViewUserCommand(Menu parentMenu) {
        super(parentMenu);
        this.name = "view user";
    }

    @Override
    public void show() {
        System.out.println("please enter a username");
    }

    @Override
    public void execute() {
        String username = scanner.nextLine();
        try {
            Person person = AccountController.getUser(username);
            System.out.println("-------------------------------\n"
                    + person.informationDisplay()
                    + "\n-------------------------------");
        } catch (UserDoesNotExistException exception) {
            System.out.println("username is not correct");
        }
        parentMenu.show();
        parentMenu.execute();
    }
}
