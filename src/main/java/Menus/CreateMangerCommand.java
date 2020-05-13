package Menus;

import Controller.BossController;
import Controller.Exeptions.DuplicateUsernameException;
import com.sun.org.apache.xerces.internal.utils.XMLLimitAnalyzer;

import java.util.ArrayList;

public class CreateMangerCommand extends Menu {
    public CreateMangerCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Please use this format to create a new manager:\ncreate manager [username] [password]");
    }

    @Override
    protected void execute() {
        String command = scanner.nextLine();
        try {
            BossController.createManager(command.split(" "));
            System.out.println("manger created successfully");
        } catch (DuplicateUsernameException exception) {
            System.out.println("this username is already exist");
        }
    }
}
