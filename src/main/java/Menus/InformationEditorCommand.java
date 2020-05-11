package Menus;

import java.util.ArrayList;

public class InformationEditorCommand extends Menu {
    public InformationEditorCommand(String name, ArrayList<Menu> subMenu) {
        super(name, subMenu);
    }

    @Override
    protected void show() {
        System.out.println("Please edit your information:");
    }

    @Override
    protected void execute() {
        String password;
        String firstName;
        String lastName;
        String phoneId;
        String email;
        System.out.println("password:");
        password = scanner.nextLine();
        System.out.println("first name:");
        firstName = scanner.nextLine();
        System.out.println("last name:");
        lastName = scanner.nextLine();
        System.out.println("phone number:");
        phoneId = scanner.nextLine();
        System.out.println("email:");
        email = scanner.nextLine();
        getUserRecursively(this).informationEditor(password, firstName, lastName, phoneId, email);
        parentMenu.show();
        parentMenu.execute();
    }
}
